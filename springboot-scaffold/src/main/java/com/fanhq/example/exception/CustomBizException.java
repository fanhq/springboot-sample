package com.fanhq.example.exception;

/**
 * Created by Hachel on 2018/12/3
 */
public class CustomBizException extends Exception {


    public CustomBizException() {
        super();
    }

    public CustomBizException(String message) {
        super(message);
    }

    public CustomBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomBizException(Throwable cause) {
        super(cause);
    }

    protected CustomBizException(String message, Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
