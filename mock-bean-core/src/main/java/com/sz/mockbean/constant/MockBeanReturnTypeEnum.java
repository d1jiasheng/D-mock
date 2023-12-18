package com.sz.mockbean.constant;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author dijiasheng
 * @date 2023/12/14
 */
public enum MockBeanReturnTypeEnum {
    OBJECT(0, "Object"),
    STRING(1, "String"),
    INTEGER(2, "Integer"),;

    private Integer code;

    private String desc;

    MockBeanReturnTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Optional<MockBeanReturnTypeEnum> getByCode(Integer code) {
        return Arrays.stream(MockBeanReturnTypeEnum.values()).filter(item -> code.equals(item.getCode())).findFirst();
    }
}
