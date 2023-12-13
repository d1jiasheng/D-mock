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

    /**
     * 被注册的类型 0方法 1局部变量
     */
    private Integer registerType;

    private List<MockBeanReturnParam> methodParameter;
}
