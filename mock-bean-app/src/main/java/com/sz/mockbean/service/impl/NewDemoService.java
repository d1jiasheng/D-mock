package com.sz.mockbean.service.impl;

import com.sz.mockbean.annotation.MockBean;
import org.springframework.stereotype.Service;

/**
 * @author dijiasheng
 * @date 2023/12/14
 */
@Service
public class NewDemoService {

    @MockBean(beanId = 32)
    public String newMock() {
        return "我试试String最近的值";
    }

}
