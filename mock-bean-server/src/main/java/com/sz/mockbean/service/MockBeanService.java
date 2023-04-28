package com.sz.mockbean.service;

import com.sz.mockbean.model.MockBeanModel;

/**
 * @author dijiasheng
 * @date 2023/4/21
 */
public interface MockBeanService {

    boolean create(MockBeanModel mockBeanModel);

    String pull(MockBeanModel mockBeanModel);


}
