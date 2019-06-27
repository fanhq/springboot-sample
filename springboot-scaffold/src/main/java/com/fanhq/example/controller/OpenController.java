package com.fanhq.example.controller;

import com.fanhq.example.dto.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanhaiqiu
 * @date 2019/6/25
 */

@RestController
@RequestMapping(value = "open")
public class OpenController {

    @RequestMapping(value = "registry", method = {RequestMethod.POST})
    public BaseResponse registry() throws Exception{
        return BaseResponse.success(null);
    }
}
