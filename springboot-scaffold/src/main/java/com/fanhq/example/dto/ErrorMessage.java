package com.fanhq.example.dto;

/**
 * @author fanhaiqiu
 * @date 2019/6/27
 */
public class ErrorMessage {
    private String url;
    private String errorMessage;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
