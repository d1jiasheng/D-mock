package com.sz.mockbean.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sz.mockbean.constant.MockBeanReturnTypeEnum;
import com.sz.mockbean.mapper.MockBeanConfigMapper;
import com.sz.mockbean.model.MockBeanRegisterConfig;
import com.sz.mockbean.model.MockBeanReturnParam;
import com.sz.mockbean.model.request.PageRequest;
import com.sz.mockbean.model.response.PageResult;
import com.sz.mockbean.model.vo.MockBeanConfigVo;
import com.sz.mockbean.model.vo.MockBeanParameterVo;
import com.sz.mockbean.model.vo.MockBeanRegisterConfigVo;
import com.sz.mockbean.po.MockBean;
import com.sz.mockbean.po.MockBeanConfig;
import com.sz.mockbean.po.MockBeanConfigExample;
import com.sz.mockbean.service.MockBeanConfigService;
import com.sz.mockbean.service.MockBeanService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Autowired
    private MockBeanService mockBeanService;

    @Override
    public boolean bulkCreateMockBeanConfig(List<MockBeanRegisterConfig> mockBeanRegisterConfig) {
        List<MockBeanConfig> existConfig = filterExistConfig(mockBeanRegisterConfig);
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

    @Override
    public PageResult<MockBeanConfig> getAllMockConfig(PageRequest req) {
        MockBeanConfigExample example = new MockBeanConfigExample();
        example.createCriteria().andIsDeleteEqualTo(0);
        long totalCount = mockBeanConfigMapper.countByExample(example);
        List<MockBeanConfig> mockBeanConfigs = mockBeanConfigMapper.selectPage(req.start(), req.getPageSize());
        return new PageResult<>(totalCount, mockBeanConfigs);
    }

    @Override
    public MockBeanConfigVo getById(Long id) {
        MockBeanConfig config = mockBeanConfigMapper.selectByPrimaryKey(id);
        MockBean mockBean = mockBeanService.getMockBean(config.getAppName(), config.getBeanId());
        return buildConfigVo(config, mockBean);
    }

    private MockBeanConfigVo buildConfigVo(MockBeanConfig config, MockBean mock) {
        MockBeanConfigVo vo = new MockBeanConfigVo();
        BeanUtils.copyProperties(config, vo);
        vo.setLatestValue(mock == null ? null : mock.getLatestValue());
        vo.setUseMock(mock == null ? 0 : mock.getUseMock());
        MockBeanReturnTypeEnum.getByCode(config.getReturnType()).ifPresent(
                mrt -> {
                    switch (mrt) {
                        case OBJECT:
                            vo.setMockValues(buildMockBeanParameters(config.getMethodParameter(), mock));
                            vo.setJsonMock(mock == null || StringUtils.isEmpty(mock.getMockValue()) ? "{}" : mock.getMockValue());
                            break;
                        case STRING:
                        case INTEGER:
                            vo.setMockValue(mock == null ? null : mock.getMockValue());
                            break;
                        default:
                            break;
                    }
                }
        );
        return vo;
    }

    private List<MockBeanParameterVo> buildMockBeanParameters(String methodParameterJson, MockBean mockBean) {
        String mockValueJson;
        if (mockBean == null || StringUtils.isEmpty(mockValueJson = mockBean.getMockValue())) {
            mockValueJson = "{}";
        }
        List<MockBeanParameterVo> result = new ArrayList<>();
        JSONArray parameterArray = JSON.parseArray(methodParameterJson);
        for (int i = 0; i < parameterArray.size(); ++i) {
            JSONObject parameterJson = parameterArray.getJSONObject(i);
            if ("serialVersionUID".equals(parameterJson.getString("paramNme"))) {
                continue;
            }
            String type = parameterJson.getString("paramType");
            String name = parameterJson.getString("paramNme");
            JSONObject valueJson = JSON.parseObject(mockValueJson);
            String value = valueJson.getString(name);
            MockBeanParameterVo vo = new MockBeanParameterVo();
            vo.setParameterType(type);
            vo.setParameterName(name);
            vo.setParameterValue(value);
            result.add(vo);
        }
        return result;
    }

    private String genParameterStr(List<String> parameters) {
        return parameters.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    private List<MockBeanConfig> filterExistConfig(List<MockBeanRegisterConfig> mockBeanRegisterConfig) {
        String appName = mockBeanRegisterConfig.get(0).getAppName();
        List<MockBeanConfig> existConfig = mockBeanConfigMapper.bulkSelectByAppNameAndBeanIds(appName,
                mockBeanRegisterConfig.stream().map(MockBeanRegisterConfig::getBeanId).collect(Collectors.toList()));
        Map<Long, MockBeanConfig> mockBeanConfigMap = existConfig
                .stream().collect(Collectors.toMap(MockBeanConfig::getBeanId, Function.identity()));
        mockBeanRegisterConfig.removeIf(config -> mockBeanConfigMap.get(config.getBeanId()) != null);
        return existConfig;
    }
}
