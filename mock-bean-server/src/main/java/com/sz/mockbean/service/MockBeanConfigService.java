package com.sz.mockbean.service;

import com.sz.mockbean.model.MockBeanRegisterConfig;

import java.util.List;

/**
 * @author dijiasheng
 * @date 2023/4/23
 */
public interface MockBeanConfigService {

    boolean bulkCreateMockBeanConfig(List<MockBeanRegisterConfig> mockBeanRegisterConfig);
}
