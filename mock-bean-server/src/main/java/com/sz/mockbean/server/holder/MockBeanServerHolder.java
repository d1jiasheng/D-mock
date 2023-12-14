package com.sz.mockbean.server.holder;

import com.alibaba.fastjson.JSON;
import com.sz.mockbean.common.mockbean.MockBeanConfig;
import com.sz.mockbean.model.MockBeanModel;
import com.sz.mockbean.model.MockBeanRegisterConfig;
import com.sz.mockbean.model.WriteValueModel;
import com.sz.mockbean.request.MockBeanProtocal;
import com.sz.mockbean.service.MockBeanConfigService;
import com.sz.mockbean.service.MockBeanService;
import com.sz.mockbean.service.service.ServerDataService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @author dijiasheng
 * @date 2023/12/13
 */
@Slf4j
public class MockBeanServerHolder implements InitializingBean {

    private MockBeanConfig mockBeanConfig;

    public MockBeanServerHolder(MockBeanConfig mockBeanConfig) {
        this.mockBeanConfig = mockBeanConfig;
    }

    @Autowired
    private MockBeanConfigService mockBeanConfigService;
    @Autowired
    private ServerDataService serverDataService;


    @Override
    public void afterPropertiesSet() throws Exception {
        //这里开启netty线程
        Thread t = new Thread(() -> {
            ServerBootstrap sbs = new ServerBootstrap();
            NioEventLoopGroup boss = new NioEventLoopGroup();
            NioEventLoopGroup worker = new NioEventLoopGroup();
            try {
                sbs.group(boss, worker).channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new IdleStateHandler(0, 0, 30 * 3, TimeUnit.SECONDS))
                                        .addLast(new StringDecoder())
                                        .addLast(new ChannelJsonInHandler())
                                        .addLast(new StringEncoder());
                            }
                        }).childOption(ChannelOption.SO_KEEPALIVE, true);
                ChannelFuture future = sbs.bind(Integer.parseInt(mockBeanConfig.getHost())).sync();
                log.info("server start succ");
                future.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                log.info("server stop.", e);
            } finally {
                boss.shutdownGracefully();
                worker.shutdownGracefully();
            }
        });
        t.start();
    }


    private class ChannelJsonInHandler extends SimpleChannelInboundHandler<String> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            try {
                MockBeanProtocal mbp = JSON.parseObject(msg, MockBeanProtocal.class);
                log.info("[ChannelJsonInHandler] channelRead0 mbp:{}", JSON.toJSONString(mbp));
                switch (mbp.getAction()) {
                    case "registry":
                        mockBeanConfigService.bulkCreateMockBeanConfig(JSON.parseArray(mbp.getMsg(), MockBeanRegisterConfig.class));
                        break;
                    case "request":
                        String result = serverDataService.getMockBeanResult(JSON.parseObject(mbp.getMsg(), MockBeanModel.class));
                        ctx.channel().writeAndFlush(JSON.toJSONString(genMockBeanProtocal(mbp.getSeqId(), result, "request")));
                        break;
                    case "write":
                        serverDataService.writeLatestValue(JSON.parseObject(mbp.getMsg(), WriteValueModel.class));
                        break;
                    default:
                        log.info("unknow message");
                        break;
                }
            } catch (Exception e) {
                log.error("[channelRead0] 出错", e);
            }

        }
    }

    private MockBeanProtocal genMockBeanProtocal(String seqId, String msg, String action) {
        MockBeanProtocal mockBeanProtocal = new MockBeanProtocal();
        mockBeanProtocal.setSeqId(seqId);
        mockBeanProtocal.setMsg(msg);
        mockBeanProtocal.setAction(action);
        return mockBeanProtocal;
    }
}
