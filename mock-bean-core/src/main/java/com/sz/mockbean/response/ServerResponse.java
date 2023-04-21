package com.sz.mockbean.response;

import lombok.Data;

/**
 * @author dijiasheng
 * @date 2023/4/21
 */
@Data
public class ServerResponse<T> {

    private Integer code;

    private String msg;

    private T data;

    public ServerResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ServerResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static<T> ServerResponse<T> success() {
        return new ServerResponse(200, "success");
    }

    public static<T> ServerResponse<T> success(T data) {
        return new ServerResponse<T>(200, "success", data);
    }
}
