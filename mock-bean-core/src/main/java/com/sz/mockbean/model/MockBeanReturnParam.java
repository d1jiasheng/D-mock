package com.sz.mockbean.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @author dijiasheng
 * @date 2023/4/28
 */
@Data
public class MockBeanReturnParam implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 7000978579848284772L;

    private String paramType;

    private String paramNme;
}
