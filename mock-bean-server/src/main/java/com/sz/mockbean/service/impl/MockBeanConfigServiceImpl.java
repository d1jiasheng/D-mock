package com.sz.mockbean.service.impl;

import com.sz.mockbean.mapper.MockBeanConfigMapper;
import com.sz.mockbean.po.MockBeanConfig;
import com.sz.mockbean.model.MockBeanRegisterConfig;
import com.sz.mockbean.service.MockBeanConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dijiasheng
 * @date 2023/4/23
 */
@Service
public class MockBeanConfigServiceImpl implements MockBeanConfigService {

    @Autowired
    private MockBeanConfigMapper mockBeanConfigMapper;

    @Override
    public boolean bulkCreateMockBeanConfig(List<MockBeanRegisterConfig> mockBeanRegisterConfig) {
        mockBeanConfigMapper.bulkInsert(mockBeanRegisterConfig.stream()
                .map(item -> {
                    MockBeanConfig mockBeanConfig = new MockBeanConfig();
                    BeanUtils.copyProperties(item, mockBeanConfig);
                    mockBeanConfig.setMethodParameter(genParameterStr(item.getMethodParameter()));
                    mockBeanConfig.setCreateTime(LocalDateTime.now());
                    mockBeanConfig.setIsDelete(0);
                    return mockBeanConfig;
                })
                .collect(Collectors.toList()));
        return true;
    }

    private String genParameterStr(List<String> parameters) {
        return parameters.stream().map(String::valueOf).collect(Collectors.joining(","));
    }
}
