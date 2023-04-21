package com.sz.mockbean.controller;

import com.alibaba.fastjson.JSON;
import com.sz.mockbean.model.MockModel;
import com.sz.mockbean.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dijiasheng
 * @date 2023/4/20
 */
@RestController
@RequestMapping("hello")
@Slf4j
public class HelloController {

    @Autowired
    private DemoService demoService;

    @ResponseBody
    @RequestMapping("test")
    public String hello() {
        log.info("hello test");
        return "hello";
    }

    @PostMapping("mockBeanTest")
    @ResponseBody
    public String mockBeanTest() {
        MockModel mockModel = demoService.mock();
        return JSON.toJSONString(mockModel);
    }
}
