package com.sz.mockbean.common.mockbean;

import com.sz.mockbean.annotation.MockBean;
import com.sz.mockbean.common.http.WebClientService;
import com.sz.mockbean.model.MockBeanModel;
import com.sz.mockbean.model.MockBeanRegisterConfig;
import com.sz.mockbean.model.MockBeanReturnParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dijiasheng
 * @date 2023/4/21
 */
@Slf4j
public class MockBeanService implements ApplicationContextAware, InitializingBean {

    private String address;
    private String host = "8080";
    private String appName;
    private boolean open;

    private static final String splitchar = "\\$\\$";
    private static WebClientService webClientService;

    private ApplicationContext applicationContext;


    public boolean isMockBeanServiceOpen() {
        return this.open;
    }

    public MockBeanService(MockBeanConfig mockBeanConfig) {
        this.open = mockBeanConfig.getServiceOpen();
        if (open) {
            this.address = mockBeanConfig.getAddress();
            this.host = StringUtils.isNotEmpty(mockBeanConfig.getHost()) ? mockBeanConfig.getHost() : this.host;
            this.appName = mockBeanConfig.getAppName();
            if (webClientService == null || !webClientService.isAlive()) {
                webClientService = new WebClientService(mockBeanConfig);
            }
        }
    }

    private void registerMockBean() {
        if (!isMockBeanServiceOpen()) {
            return;
        }
        List<MockBeanRegisterConfig> mockBeanRegisterConfigs = new ArrayList<>();
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            Class beanClz = applicationContext.getBean(beanName).getClass();
            Method[] methods = beanClz.getDeclaredMethods();
            for (Method method : methods) {
                MockBean mockBean = AnnotationUtils.findAnnotation(method, MockBean.class);
                if (mockBean == null) {
                    continue;
                }
                mockBeanRegisterConfigs.add(genRegisterConfig(mockBean, method, beanClz));
            }
        }
        String registerUri = "/main/register";
        webClientService.post(registerUri, mockBeanRegisterConfigs);
    }

    public String pullMockBean(Long beanId, String beanName, String className, String methodName) {
        MockBeanModel mockBeanModel = new MockBeanModel();
        mockBeanModel.setBeanId(beanId);
        mockBeanModel.setAppName(appName);
        mockBeanModel.setBeanName(beanName);
        mockBeanModel.setClassName(className);
        mockBeanModel.setMethodName(methodName);
        String pullUri = "/main/pull";
        return webClientService.post(pullUri, mockBeanModel);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private MockBeanRegisterConfig genRegisterConfig(MockBean mockBean, Method method, Class clz) {
        MockBeanRegisterConfig config = new MockBeanRegisterConfig();
        config.setAppName(appName);
        config.setBeanId(mockBean.beanId());
        config.setBeanName(StringUtils.isEmpty(mockBean.beanName()) ? method.getName() : mockBean.beanName());
        config.setClassName(clz.getSimpleName().split(splitchar)[0]);
        config.setMethodName(method.getName());

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

    @Override
    public void afterPropertiesSet() throws Exception {
        registerMockBean();
    }
}
