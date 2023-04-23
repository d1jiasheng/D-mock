package com.sz.mockbean.model;

import lombok.Data;

import java.util.List;

/**
 * @author dijiasheng
 * @date 2023/4/23
 */
@Data
public class MockBeanRegisterConfig {

    private Long beanId;

    private String appName;

    private String beanName;

    private String className;

    private String methodName;

    private List<String> methodParameter;
}
