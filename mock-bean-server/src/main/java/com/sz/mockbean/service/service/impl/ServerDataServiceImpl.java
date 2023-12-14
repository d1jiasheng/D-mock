package com.sz.mockbean.service.service.impl;

import com.sz.mockbean.model.MockBeanModel;
import com.sz.mockbean.model.WriteValueModel;
import com.sz.mockbean.po.MockBean;
import com.sz.mockbean.service.MockBeanService;
import com.sz.mockbean.service.service.ServerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author dijiasheng
 * @date 2023/12/14
 */
@Service
public class ServerDataServiceImpl implements ServerDataService {

    @Autowired
    private MockBeanService mockBeanService;

    @Override
    public String getMockBeanResult(MockBeanModel mockBeanModel) {
        Optional<MockBean> mockBeanOpt = mockBeanService.getBeanModel(mockBeanModel);
        return mockBeanOpt.map(
                mockBean -> {
                    if (mockBean.getUseMock() == 0) {
                        return null;
                    }
                    return mockBean.getMockValue();
                }
        ).orElse(null);
    }

    @Override
    public void writeLatestValue(WriteValueModel writeValueModel) {
        mockBeanService.updateLatestValue(writeValueModel);
    }

}
