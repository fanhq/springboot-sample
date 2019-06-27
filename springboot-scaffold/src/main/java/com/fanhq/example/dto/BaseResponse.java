package com.fanhq.example.dto;


import com.alibaba.fastjson.annotation.JSONField;
import com.fanhq.example.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author fanhaiqiu
 * @date 2019/4/9
 */
public class BaseResponse<T> implements Serializable {

    @JSONField(ordinal = 1)
    @JsonProperty(index = 1)
    private int code;

    @JSONField(ordinal = 2)
    @JsonProperty(index = 2)
    private String message;

    @JSONField(ordinal = 3)
    @JsonProperty(index = 3)
    private T data;

    /**
     * 创建成功的返回结构
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(StatusEnum.SUCCESS.getCode());
        response.setMessage(StatusEnum.SUCCESS.getMessage());
        response.setData(data);
        return response;
    }

    /**
     * 创建失败的返回结构
     *
     * @param code
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> fail(int code, String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    /**
     * 创建失败的返回结构
     *
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> invalid(T token) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setData(token);
        response.setCode(StatusEnum.INVALID.getCode());
        response.setMessage(StatusEnum.INVALID.getMessage());
        return response;
    }

    /**
     * 创建builder
     *
     * @param <T>
     * @return
     */
    public static <T> Builder<T> builder() {
        return new Builder<T>();
    }

    public static class Builder<t> {

        private int code;

        private String message;

        private t data;

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(t data) {
            this.data = data;
            return this;
        }

        public BaseResponse<t> build() {
            BaseResponse<t> response = new BaseResponse();
            response.setData(this.data);
            response.setCode(this.code);
            response.setMessage(this.message);
            return response;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
