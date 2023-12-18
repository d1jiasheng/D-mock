package com.sz.mockbean.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author dijiasheng
 * @date 2023/12/15
 */
@Data
public class MockBeanConfigVo implements Serializable {
    private static final long serialVersionUID = 3507025800960698579L;

    private Long id;

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

    private Integer returnType;

    private Integer useMock;

    private List<MockBeanParameterVo> mockValues;

    private String mockValue;

    private String latestValue;

    private String JsonMock;

    private Integer mockType = 0; //0.参数 1.JSON
}
