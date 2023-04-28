package com.sz.mockbean.model.response;

import lombok.Data;

/**
 * @author dijiasheng
 * @date 2023/4/28
 */
@Data
public class CmsResponse<T> {

    private Integer code;

    private String msg;

    private T data;

    public CmsResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CmsResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> CmsResponse<T> success() {
        return new CmsResponse(200, "success");
    }

    public static <T> CmsResponse<T> success(T data) {
        return new CmsResponse<T>(200, "success", data);
    }

    public static <T> CmsResponse<T> fail() {
        return new CmsResponse(400, "fail");
    }

}
