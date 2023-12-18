package com.sz.mockbean.model.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dijiasheng
 * @date 2023/12/15
 */
@Data
public class MockBeanConfigVo implements Serializable {
    private static final long serialVersionUID = 3507025800960698579L;

    private String appName;

    /**
     *
     */
    private Long beanId;

    /**
     *
     */
    private String beanName;

    /**
     *
     */
    private String className;

    /**
     *
     */
    private String methodName;
}
