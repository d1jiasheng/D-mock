package com.sz.mockbean.service;

import com.sz.mockbean.model.MockBeanRegisterConfig;
import com.sz.mockbean.model.request.PageRequest;
import com.sz.mockbean.model.response.PageResult;
import com.sz.mockbean.model.vo.MockBeanConfigVo;
import com.sz.mockbean.model.vo.MockBeanRegisterConfigVo;
import com.sz.mockbean.po.MockBeanConfig;

import java.util.List;

/**
 * @author dijiasheng
 * @date 2023/4/23
 */
public interface MockBeanConfigService {

    boolean bulkCreateMockBeanConfig(List<MockBeanRegisterConfig> mockBeanRegisterConfig);

    List<MockBeanRegisterConfigVo> getAllMockConfigByConditions(String appName, Long beanId);

    PageResult<MockBeanConfig> getAllMockConfig(PageRequest req);

    MockBeanConfigVo getById(Long id);

}
