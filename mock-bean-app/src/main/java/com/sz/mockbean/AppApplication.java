package com.sz.mockbean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author dijiasheng
 * @date 2023/4/20
 */
@SpringBootApplication
@EnableAutoConfiguration
public class AppApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

}
