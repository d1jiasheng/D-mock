package com.sz.mockbean.controller;

import com.alibaba.fastjson.JSON;
import com.sz.mockbean.model.MockBeanModel;
import com.sz.mockbean.model.MockBeanRegisterConfig;
import com.sz.mockbean.response.ServerResponse;
import com.sz.mockbean.service.MockBeanConfigService;
import com.sz.mockbean.service.MockBeanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dijiasheng
 * @date 2023/4/21
 */
@Slf4j
@RestController
@RequestMapping("main")
public class MainController {

    @Autowired
    private MockBeanService mockBeanService;
    @Autowired
    private MockBeanConfigService mockBeanConfigService;

    @ResponseBody
    @PostMapping("register")
    public ServerResponse<Boolean> register(@RequestBody List<MockBeanRegisterConfig> mockBeanRegisterConfigs) {
        log.info("[mockBean 服务端] mockBean注册接口入参 mockBeanModel:{}", JSON.toJSONString(mockBeanRegisterConfigs));
        if (CollectionUtils.isEmpty(mockBeanRegisterConfigs)) {
            return ServerResponse.success();
        }
        return ServerResponse.success(mockBeanConfigService.bulkCreateMockBeanConfig(mockBeanRegisterConfigs));
    }

    @ResponseBody
    @PostMapping("pull")
    public ServerResponse<String> pull(@RequestBody MockBeanModel mockBeanModel) {
        log.info("[mockBean 服务端] mockBean获取接口入参 mockBeanModel:{}", JSON.toJSONString(mockBeanModel));
        String result = mockBeanService.pull(mockBeanModel);
        log.info("[mockBean 服务端] mockBean获取接口出参 result:{}", result);
        return ServerResponse.success(result);
    }

}
