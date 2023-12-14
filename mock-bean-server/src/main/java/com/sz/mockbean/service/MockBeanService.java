package com.sz.mockbean.service;

import com.sz.mockbean.model.MockBeanModel;
import com.sz.mockbean.model.WriteValueModel;
import com.sz.mockbean.po.MockBean;

import java.util.Optional;

/**
 * @author dijiasheng
 * @date 2023/4/21
 */
public interface MockBeanService {

    boolean create(MockBeanModel mockBeanModel);

    void updateLatestValue(WriteValueModel writeValueModel);

    String pull(MockBeanModel mockBeanModel);

    Optional<MockBean> getBeanModel(MockBeanModel mockBeanModel);


}
