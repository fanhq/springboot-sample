package com.fanhq.example.enums;

/**
 * @author fanhaiqiu
 * @date 2019/6/25
 * @description 自定义业务返回编码
 */
public enum StatusEnum {

    //成功
    SUCCESS(0, "操作成功"),
    //失败
    FAIL(1, "操作失败"),
    //无效的token
    INVALID(2, "无效的token");

    /**
     * 枚举编码
     */
    private final Integer code;

    /**
     * 枚举值
     */
    private final String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
