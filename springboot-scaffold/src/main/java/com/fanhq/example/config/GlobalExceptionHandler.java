package com.fanhq.example.config;


import com.fanhq.example.dto.BaseResponse;
import com.fanhq.example.dto.ErrorMessage;
import com.fanhq.example.enums.StatusEnum;
import com.fanhq.example.exception.CustomBizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fanhaiqiu
 * @date 2019/6/27
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse<ErrorMessage> defaultErrorHandler(HttpServletRequest request, Exception e) {
        LOGGER.error("【全局异常捕获】", e);
        BaseResponse<ErrorMessage> response = new BaseResponse<>();
        response.setCode(StatusEnum.FAIL.getCode());
        //自定义业务错误
        if (e instanceof CustomBizException) {
            response.setMessage(e.getMessage());
            return response;
        }
        //系统错误
        response.setMessage(StatusEnum.FAIL.getMessage());
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorMessage(e.getMessage());
        errorMessage.setUrl(request.getRequestURL().toString());
        response.setData(errorMessage);
        return response;
    }


}

