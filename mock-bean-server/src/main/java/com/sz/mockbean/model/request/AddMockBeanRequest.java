package com.sz.mockbean.model.request;

import lombok.Data;

/**
 * @author dijiasheng
 * @date 2023/4/28
 */
@Data
public class AddMockBeanRequest {

    private String appName;

    private Long beanId;

    private String mockValue;
}
