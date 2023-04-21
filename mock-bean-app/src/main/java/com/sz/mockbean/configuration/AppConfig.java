package com.sz.mockbean.configuration;

import com.sz.mockbean.annotation.MockBeanAop;
import com.sz.mockbean.common.mockbean.MockBeanConfig;
import com.sz.mockbean.common.mockbean.MockBeanService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;

/**
 * @author dijiasheng
 * @date 2023/4/21
 */
@Configuration
public class AppConfig {

    @Bean
    public MockBeanService mockBeanService() throws FileNotFoundException {
        MockBeanConfig mockBeanConfig = new MockBeanConfig("/config/mockbean.properties");
        return mockBeanConfig.init();
    }

    @Bean
    public MockBeanAop mockBeanAop(MockBeanService mockBeanService) {
        return new MockBeanAop(mockBeanService);
    }
}
