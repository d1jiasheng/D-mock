package com.sz.mockbean.service.impl;

import com.sz.mockbean.mapper.MockBeanMapper;
import com.sz.mockbean.model.MockBeanModel;
import com.sz.mockbean.model.request.AddMockBeanRequest;
import com.sz.mockbean.po.MockBean;
import com.sz.mockbean.po.MockBeanExample;
import com.sz.mockbean.service.MockBeanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public String pull(MockBeanModel mockBeanModel) {
        MockBeanExample mockBeanExample = new MockBeanExample();
        mockBeanExample.createCriteria().andAppNameEqualTo(mockBeanModel.getAppName())
                .andBeanIdEqualTo(mockBeanModel.getBeanId())
                .andClassNameEqualTo(mockBeanModel.getClassName())
                .andMethodNameEqualTo(mockBeanModel.getMethodName())
                .andIsDeleteEqualTo(0);
        return mockBeanMapper.selectByExample(mockBeanExample).get(0).getMockValue();
    }
}
