package com.sz.mockbean.model;

import com.alibaba.fastjson.JSON;
import com.sz.mockbean.annotation.MockBean;
import com.sz.mockbean.common.mockbean.MockBeanConfig;
import com.sz.mockbean.option.OperateService;
import com.sz.mockbean.request.MockBeanProtocal;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author dijiasheng
 * @date 2023/12/13
 */
@Slf4j
public class MockBeanClientHolder implements InitializingBean, ApplicationContextAware {

    private static final String splitchar = "\\$\\$";
    private static final ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

    private ApplicationContext applicationContext;

    private MockBeanConfig mockBeanConfig;

    private Channel channel;

    public MockBeanClientHolder(MockBeanConfig mockBeanConfig) {
        this.mockBeanConfig = mockBeanConfig;
    }

    public Channel getChannel() {
        return this.channel;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Thread t = new Thread(() -> {
            Bootstrap bs = new Bootstrap();
            NioEventLoopGroup worker = new NioEventLoopGroup();
            try {
                bs.group(worker).channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new IdleStateHandler(0, 0, 30 * 3, TimeUnit.SECONDS))
                                        .addLast(new StringDecoder())
                                        .addLast(new ChannelJsonInHandler())
                                        .addLast(new StringEncoder());
                            }
                        }).option(ChannelOption.SO_KEEPALIVE, true);
                ChannelFuture f = bs.connect(mockBeanConfig.getAddress(), Integer.parseInt(mockBeanConfig.getHost())).sync();
                this.channel = f.channel();
                //开始注册
                init();
                f.channel().closeFuture().sync();
            } catch (Exception e) {
                log.error("[MockBeanClientHolder] error", e);
            } finally {
                worker.shutdownGracefully();

            }
        });
        t.start();
    }

    private void init() {
        //所有需要注册的...
        List<MockBeanRegisterConfig> mockBeanRegisterConfigs = new ArrayList<>();
        //注册所有被@MockBean注解的类、方法、字段等
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            Class beanClz = applicationContext.getBean(beanName).getClass();
            Method[] methods = beanClz.getDeclaredMethods();
            for (Method method : methods) {
                MockBean mockBean = AnnotationUtils.findAnnotation(method, MockBean.class);
                if (mockBean == null) {
                    continue;
                }
                mockBeanRegisterConfigs.add(genRegisterMethodConfig(mockBean, method, beanClz));
            }
        }
        //注册到server
        if (!CollectionUtils.isEmpty(mockBeanRegisterConfigs)) {
            OperateService.doRegistryOperate(channel, mockBeanRegisterConfigs);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private class ChannelJsonInHandler extends SimpleChannelInboundHandler<String> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) {
            MockBeanProtocal readRes = JSON.parseObject(msg, MockBeanProtocal.class);
            map.put(readRes.getSeqId(), readRes);
        }
    }

    public String write(MockBeanProtocal p) throws InterruptedException {
        map.put(p.getSeqId(), new MockBeanProtocal());
        this.channel.writeAndFlush(JSON.toJSONString(p)).sync();
        Thread.sleep(100);
        return JSON.toJSONString(map.remove(p.getSeqId()));
    }

    private MockBeanRegisterConfig genRegisterMethodConfig(MockBean mockBean, Method method, Class clz) {
        MockBeanRegisterConfig config = new MockBeanRegisterConfig();
        config.setAppName(mockBeanConfig.getAppName());
        config.setBeanId(mockBean.beanId());
        config.setBeanName(StringUtils.isEmpty(mockBean.beanName()) ? method.getName() : mockBean.beanName());
        config.setClassName(clz.getSimpleName().split(splitchar)[0]);
        config.setMethodName(method.getName());
        config.setRegisterType(0);

        List<MockBeanReturnParam> returnParams = new ArrayList<>();
        Field[] methodField = method.getReturnType().getDeclaredFields();
        for (Field field : methodField) {
            MockBeanReturnParam returnParam = new MockBeanReturnParam();
            returnParam.setParamNme(field.getName());
            returnParam.setParamType(field.getType().getSimpleName());
            returnParams.add(returnParam);
        }
        config.setMethodParameter(returnParams);
        return config;
    }

}
