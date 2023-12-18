package com.sz.mockbean.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dijiasheng
 * @date 2023/12/18
 */
@Data
public class MockBeanParameterVo implements Serializable {
    private static final long serialVersionUID = 5412288127303213396L;

    private String parameterName;

    private String parameterType;

    private String parameterValue;
}
