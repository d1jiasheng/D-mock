package com.sz.mockbean.controller;

import com.alibaba.fastjson.JSON;
import com.sz.mockbean.model.MockBeanModel;
import com.sz.mockbean.response.ServerResponse;
import com.sz.mockbean.service.MockBeanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ResponseBody
    @PostMapping("register")
    public ServerResponse<Boolean> register(@RequestBody MockBeanModel mockBeanModel) {
        log.info("[mockBean 服务端] mockBean注册接口入参 mockBeanModel:{}", JSON.toJSONString(mockBeanModel));
        return ServerResponse.success(mockBeanService.create(mockBeanModel));
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
