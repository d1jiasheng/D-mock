package com.sz.mockbean.controller;

import com.sz.mockbean.model.request.PageRequest;
import com.sz.mockbean.model.response.CmsResponse;
import com.sz.mockbean.model.response.PageResult;
import com.sz.mockbean.model.vo.MockBeanConfigVo;
import com.sz.mockbean.po.MockBean;
import com.sz.mockbean.po.MockBeanConfig;
import com.sz.mockbean.service.MockBeanConfigService;
import com.sz.mockbean.service.MockBeanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private MockBeanService mockBeanService;

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

    @ResponseBody
    @GetMapping("detail")
    public CmsResponse<MockBeanConfigVo> configDetail(Long id) {
        MockBeanConfigVo vo = mockBeanConfigService.getById(id);
        return CmsResponse.success(vo);
    }

    @ResponseBody
    @PostMapping("post")
    public CmsResponse configUpdate(@RequestBody MockBeanConfigVo configVo) {
        MockBean bean = mockBeanService.getMockBean(configVo.getAppName(), configVo.getBeanId());
        if (bean == null) { //创建
            mockBeanService.createMockBean(configVo);
            return CmsResponse.success();
        }
        mockBeanService.updateMockBean(configVo);
        return CmsResponse.success();
    }


}
