package com.sz.mockbean.controller;

import com.sz.mockbean.model.MockBeanModel;
import com.sz.mockbean.model.request.AddMockBeanRequest;
import com.sz.mockbean.model.response.CmsResponse;
import com.sz.mockbean.model.vo.MockBeanRegisterConfigVo;
import com.sz.mockbean.service.MockBeanConfigService;
import com.sz.mockbean.service.MockBeanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dijiasheng
 * @date 2023/4/28
 */
@Slf4j
@RestController
@RequestMapping("service")
public class ServiceController {

    @Autowired
    private MockBeanConfigService mockBeanConfigService;
    @Autowired
    private MockBeanService mockBeanService;

    @ResponseBody
    @GetMapping("getAll")
    public CmsResponse getAllMockBeanConfig(String appName, Long beanId) {
        return CmsResponse.success(mockBeanConfigService.getAllMockConfigByConditions(appName, beanId));
    }

    @ResponseBody
    @PostMapping("addMockBean")
    public CmsResponse addMockBean(AddMockBeanRequest request) {
        List<MockBeanRegisterConfigVo> mockBeanRegisterConfigVos = mockBeanConfigService.getAllMockConfigByConditions
                (request.getAppName(), request.getBeanId());
        if (CollectionUtils.isEmpty(mockBeanRegisterConfigVos)) {
            return CmsResponse.fail();
        }
        if (mockBeanRegisterConfigVos.size() > 1) {
            return CmsResponse.fail();
        }
        MockBeanModel mockBeanModel = new MockBeanModel();
        BeanUtils.copyProperties(mockBeanRegisterConfigVos.get(0), mockBeanModel);
        mockBeanModel.setMockValue(request.getMockValue());
        mockBeanService.create(mockBeanModel);
        return CmsResponse.success();
    }
}
