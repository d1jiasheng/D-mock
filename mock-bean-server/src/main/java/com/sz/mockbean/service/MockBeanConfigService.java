package com.sz.mockbean.service;

import com.sz.mockbean.model.MockBeanRegisterConfig;
import com.sz.mockbean.model.vo.MockBeanRegisterConfigVo;

import java.util.List;

/**
 * @author dijiasheng
 * @date 2023/4/23
 */
public interface MockBeanConfigService {

    boolean bulkCreateMockBeanConfig(List<MockBeanRegisterConfig> mockBeanRegisterConfig);

    List<MockBeanRegisterConfigVo> getAllMockConfigByConditions(String appName, Long beanId);
}
