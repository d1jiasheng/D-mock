package com.sz.mockbean.model.vo;

import com.sz.mockbean.model.MockBeanReturnParam;
import lombok.Data;

import java.util.List;

/**
 * @author dijiasheng
 * @date 2023/4/28
 */
@Data
public class MockBeanRegisterConfigVo {

    private String appName;

    private Long beanId;

    private String beanName;

    private String className;

    private String methodName;

    private List<MockBeanReturnParam> mockBeanReturnParamList;
}
