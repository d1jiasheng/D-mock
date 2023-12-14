package com.sz.mockbean.model;

import lombok.Data;

/**
 * @author dijiasheng
 * @date 2023/12/14
 */
@Data
public class WriteValueModel extends BaseMockBeanModel {
    private static final long serialVersionUID = -5305412496251336609L;

    private String latestValue;
}
