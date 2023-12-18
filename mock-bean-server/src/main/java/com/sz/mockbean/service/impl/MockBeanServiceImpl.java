package com.sz.mockbean.service.impl;

import com.alibaba.fastjson.JSON;
import com.sz.mockbean.mapper.MockBeanMapper;
import com.sz.mockbean.model.MockBeanModel;
import com.sz.mockbean.model.WriteValueModel;
import com.sz.mockbean.model.vo.MockBeanConfigVo;
import com.sz.mockbean.model.vo.MockBeanParameterVo;
import com.sz.mockbean.po.MockBean;
import com.sz.mockbean.po.MockBeanExample;
import com.sz.mockbean.service.MockBeanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author dijiasheng
 * @date 2023/4/21
 */
@Service
public class MockBeanServiceImpl implements MockBeanService {

    @Autowired
    private MockBeanMapper mockBeanMapper;


    @Override
    public boolean create(MockBeanModel mockBeanModel) {
        MockBean mockBean = new MockBean();
        BeanUtils.copyProperties(mockBeanModel, mockBean);
        mockBean.setCreateTime(LocalDateTime.now());
        mockBean.setUpdateTime(LocalDateTime.now());
        mockBeanMapper.insertSelective(mockBean);
        return true;
    }

    @Override
    public void updateLatestValue(WriteValueModel writeValueModel) {
        MockBean mockBean = new MockBean();
        mockBean.setLatestValue(writeValueModel.getLatestValue());
        mockBean.setUpdateTime(LocalDateTime.now());
        MockBeanExample mockBeanExample = new MockBeanExample();
        mockBeanExample.createCriteria().andAppNameEqualTo(writeValueModel.getAppName())
                .andBeanIdEqualTo(writeValueModel.getBeanId())
                .andClassNameEqualTo(writeValueModel.getClassName())
                .andMethodNameEqualTo(writeValueModel.getMethodName());
        mockBeanMapper.updateByExampleSelective(mockBean, mockBeanExample);
    }

    @Override
    public String pull(MockBeanModel mockBeanModel) {
        MockBeanExample mockBeanExample = new MockBeanExample();
        mockBeanExample.createCriteria().andAppNameEqualTo(mockBeanModel.getAppName())
                .andBeanIdEqualTo(mockBeanModel.getBeanId())
                .andClassNameEqualTo(mockBeanModel.getClassName())
                .andMethodNameEqualTo(mockBeanModel.getMethodName())
                .andIsDeleteEqualTo(0);
        return mockBeanMapper.selectByExample(mockBeanExample).get(0).getMockValue();
    }

    @Override
    public Optional<MockBean> getBeanModel(MockBeanModel mockBeanModel) {
        MockBeanExample mockBeanExample = new MockBeanExample();
        mockBeanExample.createCriteria().andAppNameEqualTo(mockBeanModel.getAppName())
                .andBeanIdEqualTo(mockBeanModel.getBeanId())
                .andClassNameEqualTo(mockBeanModel.getClassName())
                .andMethodNameEqualTo(mockBeanModel.getMethodName())
                .andIsDeleteEqualTo(0);
        List<MockBean> mockBeanList = mockBeanMapper.selectByExample(mockBeanExample);
        if (CollectionUtils.isEmpty(mockBeanList)) {
            return Optional.empty();
        }
        return Optional.ofNullable(mockBeanList.get(0));
    }

    @Override
    public MockBean getMockBean(String appName, Long beanId) {
        MockBeanExample example = new MockBeanExample();
        example.createCriteria().andAppNameEqualTo(appName)
                .andBeanIdEqualTo(beanId);
        List<MockBean> mockBeans = mockBeanMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(mockBeans)) {
            return null;
        }
        return mockBeans.get(0);
    }

    @Override
    public boolean updateMockBean(MockBeanConfigVo configVo) {
        MockBeanExample example = new MockBeanExample();
        example.createCriteria().andBeanIdEqualTo(configVo.getBeanId())
                .andAppNameEqualTo(configVo.getAppName());
        MockBean mockBean = new MockBean();
        mockBean.setUseMock(configVo.getUseMock());
        if (configVo.getMockType() == 1) {
            mockBean.setMockValue(configVo.getJsonMock());
        } else {
            mockBean.setMockValue(buildMockValue(configVo.getReturnType(), configVo.getMockValue(), configVo.getMockValues()));
        }
        mockBean.setUpdateTime(LocalDateTime.now());
        mockBeanMapper.updateByExampleSelective(mockBean, example);
        return true;
    }

    @Override
    public boolean createMockBean(MockBeanConfigVo configVo) {
        MockBean mockBean = new MockBean();
        BeanUtils.copyProperties(configVo, mockBean);
        if (configVo.getMockType() == 1) {
            mockBean.setMockValue(configVo.getJsonMock());
        } else {
            mockBean.setMockValue(buildMockValue(configVo.getReturnType(), configVo.getMockValue(), configVo.getMockValues()));
        }
        mockBean.setMockValue(buildMockValue(configVo.getReturnType(), configVo.getMockValue(), configVo.getMockValues()));
        mockBean.setCreateTime(LocalDateTime.now());
        mockBeanMapper.insertSelective(mockBean);
        return true;
    }

    private String buildMockValue(Integer returnType, String mockValue, List<MockBeanParameterVo> mockValues) {
        if (returnType == 0) {
            Map<String, String> parameterMap = new HashMap<>();
            for (MockBeanParameterVo parameterVo : mockValues) {
                parameterMap.put(parameterVo.getParameterName(), parameterVo.getParameterValue());
            }
            return JSON.toJSONString(parameterMap);
        }
        return mockValue;
    }
}
