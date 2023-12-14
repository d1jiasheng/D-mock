package com.sz.mockbean.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dijiasheng
 * @date 2023/12/14
 */
@Data
public class BaseMockBeanModel implements Serializable {
    private static final long serialVersionUID = 5677781880633785198L;

    private Long beanId;

    private String appName;

    private String beanName;

    private String className;

    private String methodName;
}
