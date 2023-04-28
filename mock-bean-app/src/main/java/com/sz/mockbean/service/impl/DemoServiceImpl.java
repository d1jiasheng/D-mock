package com.sz.mockbean.service.impl;

import com.sz.mockbean.annotation.MockBean;
import com.sz.mockbean.model.MockModel;
import com.sz.mockbean.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * @author dijiasheng
 * @date 2023/4/20
 */
@Service
public class DemoServiceImpl implements DemoService {

    @MockBean(beanId = 1)
    @Override
    public MockModel mock() {
        return null;
    }

    @MockBean(beanId = 2)
    @Override
    public MockModel batchMock() {
        return null;
    }
}
