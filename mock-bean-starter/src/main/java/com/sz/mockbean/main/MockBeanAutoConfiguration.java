package com.sz.mockbean.main;

import com.sz.mockbean.common.mockbean.MockBeanConfig;
import com.sz.mockbean.other.MockBeanClientHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dijiasheng
 * @date 2023/7/17
 */
@Configuration
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
    public MockBeanClientHolder holder() throws Exception {
        MockBeanConfig mockBeanConfig = new MockBeanConfig("/config/mockbean.properties");
        return new MockBeanClientHolder(mockBeanConfig);
    }

}
