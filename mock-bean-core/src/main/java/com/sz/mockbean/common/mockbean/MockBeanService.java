package com.sz.mockbean.common.mockbean;

import com.sz.mockbean.common.http.WebClientService;
import com.sz.mockbean.model.MockBeanModel;
import org.apache.commons.lang3.StringUtils;

/**
 * @author dijiasheng
 * @date 2023/4/21
 */
public class MockBeanService {

    private String address;
    private String host = "8080";
    private String appName;

    private static WebClientService webClientService;


    public MockBeanService(MockBeanConfig mockBeanConfig) {
        this.address = mockBeanConfig.getAddress();
        this.host = StringUtils.isNotEmpty(mockBeanConfig.getHost()) ? mockBeanConfig.getHost() : this.host;
        this.appName = mockBeanConfig.getAppName();
        if (webClientService == null || !webClientService.isAlive()) {
            webClientService = new WebClientService(mockBeanConfig);
        }
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
}
