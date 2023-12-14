package com.sz.mockbean.controller;

import com.sz.mockbean.model.MockBeanClientHolder;
import com.sz.mockbean.model.MockModel;
import com.sz.mockbean.request.MockBeanProtocal;
import com.sz.mockbean.service.DemoService;
import com.sz.mockbean.service.impl.NewDemoService;
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
    @Autowired
    private MockBeanClientHolder holder;
    @Autowired
    private NewDemoService newDemoService;

    @ResponseBody
    @RequestMapping("test")
    public String hello() throws Exception {
        MockBeanProtocal p = new MockBeanProtocal();
        p.setSeqId("2131dwqd2d2d");
        p.setAction("registry");
        return holder.write(p);
    }

    @PostMapping("mockBeanTest")
    @ResponseBody
    public String mockBeanTest() {
        MockModel mockModel = demoService.mock();
        return mockModel.getName();
    }
}
