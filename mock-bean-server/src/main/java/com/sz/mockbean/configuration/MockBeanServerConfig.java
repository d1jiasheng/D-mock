package com.sz.mockbean.configuration;

import com.sz.mockbean.common.mockbean.MockBeanConfig;
import com.sz.mockbean.server.holder.MockBeanServerHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;

/**
 * @author dijiasheng
 * @date 2023/12/13
 */
@Configuration
public class MockBeanServerConfig {

    @Bean
    public MockBeanServerHolder holder() throws FileNotFoundException {
        MockBeanConfig mockBeanConfig = new MockBeanConfig("/config/mockbean.properties");
        return new MockBeanServerHolder(mockBeanConfig);
    }
}
