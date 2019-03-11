package com.fanhq.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fanhaiqiu
 * @date 2019/3/1
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String defaultErrorHandler(HttpServletRequest req, Exception e) {
        LOGGER.info("【url】:{}, 【message】:{}, 【】", req.getRequestURL().toString(), e.getMessage());
        LOGGER.error("异常信息", e);
        return e.getMessage();
    }




}
