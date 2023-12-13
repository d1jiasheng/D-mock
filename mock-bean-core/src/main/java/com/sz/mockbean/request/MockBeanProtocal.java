package com.sz.mockbean.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dijiasheng
 * @date 2023/12/13
 */
@Data
public class MockBeanProtocal implements Serializable {
    private static final long serialVersionUID = 5088405040317383183L;
    /**
     * 请求序列号
     */
    private String seqId;
    /**
     * 请求动作
     */
    private String action;
    /**
     * 请求时间
     */
    private String time;
    /**
     * 传递的具体信息
     */
    private String msg;
}
