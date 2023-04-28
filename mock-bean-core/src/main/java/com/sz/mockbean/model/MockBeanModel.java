package com.sz.mockbean.model;

import lombok.Data;

/**
 * @author dijiasheng
 * @date 2023/4/21
 */
@Data
public class MockBeanModel {

    private Long beanId;

    private String appName;

    private String beanName;

    private String className;

    private String methodName;

    private String mockValue;

}
