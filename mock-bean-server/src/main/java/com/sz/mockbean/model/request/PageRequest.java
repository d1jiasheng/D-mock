package com.sz.mockbean.model.request;

import lombok.Data;

/**
 * @author dijiasheng
 * @date 2023/12/15
 */
@Data
public class PageRequest {

    private Integer pageNum = 1;

    private Integer pageSize = 20;

    public int start() {
        return (pageNum - 1) * pageSize;
    }
}