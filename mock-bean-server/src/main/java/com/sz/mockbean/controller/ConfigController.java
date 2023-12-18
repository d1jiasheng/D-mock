package com.sz.mockbean.controller;

import com.sz.mockbean.model.request.PageRequest;
import com.sz.mockbean.model.response.CmsResponse;
import com.sz.mockbean.model.response.MockBeanConfigVo;
import com.sz.mockbean.model.response.PageResult;
import com.sz.mockbean.po.MockBeanConfig;
import com.sz.mockbean.service.MockBeanConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dijiasheng
 * @date 2023/12/15
 */
@Slf4j
@RestController
@RequestMapping("config")
public class ConfigController {

    @Autowired
    private MockBeanConfigService mockBeanConfigService;

    @ResponseBody
    @GetMapping("list")
    public CmsResponse<List<MockBeanConfigVo>> configList(PageRequest request) {
        PageResult<MockBeanConfig> result = mockBeanConfigService.getAllMockConfig(request);
        List<MockBeanConfigVo> vos = result.getPage().stream()
                .map(item -> {
                    MockBeanConfigVo vo = new MockBeanConfigVo();
                    BeanUtils.copyProperties(item, vo);
                    return vo;
                })
                .collect(Collectors.toList());
        return CmsResponse.success(vos, result.getTotalCount(), request.getPageSize());
    }

}
