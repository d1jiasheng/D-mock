package com.sz.mockbean.service.service;

import com.sz.mockbean.model.MockBeanModel;
import com.sz.mockbean.model.WriteValueModel;

/**
 * @author dijiasheng
 * @date 2023/12/14
 */
public interface ServerDataService {

    String getMockBeanResult(MockBeanModel mockBeanModel);

    void writeLatestValue(WriteValueModel writeValueModel);
}
