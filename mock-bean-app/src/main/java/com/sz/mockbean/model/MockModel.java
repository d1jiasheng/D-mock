package com.sz.mockbean.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dijiasheng
 * @date 2023/4/21
 */
@Data
public class MockModel implements Serializable {

    private static final long serialVersionUID = -6330076102807023679L;
    private String name;
}
