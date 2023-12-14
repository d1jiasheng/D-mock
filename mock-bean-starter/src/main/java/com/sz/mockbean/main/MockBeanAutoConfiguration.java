package com.sz.mockbean.main;

import com.sz.mockbean.annotation.MockBeanAop;
import com.sz.mockbean.common.mockbean.MockBeanConfig;
import com.sz.mockbean.model.MockBeanClientHolder;
import org.springframework.context.annotation.Bean;

/**
 * @author dijiasheng
 * @date 2023/7/17
 */
public class MockBeanAutoConfiguration {

//    @Bean
//    public MockBeanService mockBeanService() throws FileNotFoundException {
//        MockBeanConfig mockBeanConfig = new MockBeanConfig("/config/mockbean.properties");
//        return mockBeanConfig.init();
//    }
//
//    @Bean
//    public MockBeanAop mockBeanAop(MockBeanService mockBeanService) {
//        return new MockBeanAop(mockBeanService);
//    }

    @Bean
    public MockBeanConfig mockBeanConfig() throws Exception {
        return new MockBeanConfig("/config/mockbean.properties");
    }

    @Bean
    public MockBeanClientHolder holder(MockBeanConfig mockBeanConfig) {
        return new MockBeanClientHolder(mockBeanConfig);
    }

    @Bean
    public MockBeanAop mockBeanAop(MockBeanClientHolder holder, MockBeanConfig mockBeanConfig) {
        return new MockBeanAop(holder, mockBeanConfig);
    }

}
