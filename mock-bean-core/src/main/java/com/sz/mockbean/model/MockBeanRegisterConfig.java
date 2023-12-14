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
     * 被注册的类型 0方法
     */
    private Integer registerType;
    /**
     * 返回类型
     * @see com.sz.mockbean.constant.MockBeanReturnTypeEnum
     */
    private Integer returnType;

    private List<MockBeanReturnParam> methodParameter;
}
