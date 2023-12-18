package com.sz.mockbean.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dijiasheng
 * @date 2023/12/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {

    private long totalCount;

    private List<T> page;
}
