package com.sz.mockbean.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.sz.mockbean.mapper.MockBeanConfigMapper;
import com.sz.mockbean.model.MockBeanRegisterConfig;
import com.sz.mockbean.model.MockBeanReturnParam;
import com.sz.mockbean.model.vo.MockBeanRegisterConfigVo;
import com.sz.mockbean.po.MockBeanConfig;
import com.sz.mockbean.po.MockBeanConfigExample;
import com.sz.mockbean.service.MockBeanConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
        filterExistConfig(mockBeanRegisterConfig);
        if (CollectionUtils.isEmpty(mockBeanRegisterConfig)) {
            return true;
        }
        mockBeanConfigMapper.bulkInsert(mockBeanRegisterConfig.stream()
                .map(item -> {
                    MockBeanConfig mockBeanConfig = new MockBeanConfig();
                    BeanUtils.copyProperties(item, mockBeanConfig);
                    mockBeanConfig.setMethodParameter(JSON.toJSONString(item.getMethodParameter()));
                    mockBeanConfig.setCreateTime(LocalDateTime.now());
                    mockBeanConfig.setIsDelete(0);
                    return mockBeanConfig;
                })
                .collect(Collectors.toList()));
        return true;
    }

    @Override
    public List<MockBeanRegisterConfigVo> getAllMockConfigByConditions(String appName, Long beanId) {
        MockBeanConfigExample example = new MockBeanConfigExample();
        MockBeanConfigExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(appName)) {
            criteria.andAppNameEqualTo(appName);
        }
        if (beanId != null) {
            criteria.andBeanIdEqualTo(beanId);
        }
        criteria.andIsDeleteEqualTo(0);
        List<MockBeanConfig> mockBeanConfigs = mockBeanConfigMapper.selectByExample(example);
        return mockBeanConfigs.stream().map(item -> {
            MockBeanRegisterConfigVo target = new MockBeanRegisterConfigVo();
            BeanUtils.copyProperties(item, target);
            target.setMockBeanReturnParamList(JSON.parseArray(item.getMethodParameter(), MockBeanReturnParam.class));
            return target;
        }).collect(Collectors.toList());
    }

    private String genParameterStr(List<String> parameters) {
        return parameters.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    private void filterExistConfig(List<MockBeanRegisterConfig> mockBeanRegisterConfig) {
        String appName = mockBeanRegisterConfig.get(0).getAppName();
        Map<Long, MockBeanConfig> mockBeanConfigMap = mockBeanConfigMapper.bulkSelectByAppNameAndBeanIds(appName,
                mockBeanRegisterConfig.stream().map(MockBeanRegisterConfig::getBeanId).collect(Collectors.toList()))
                .stream().collect(Collectors.toMap(MockBeanConfig::getBeanId, Function.identity()));
        mockBeanRegisterConfig.removeIf(config -> mockBeanConfigMap.get(config.getBeanId()) != null);
    }
}
