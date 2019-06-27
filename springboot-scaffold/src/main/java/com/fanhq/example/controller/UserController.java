package com.fanhq.example.controller;

import com.fanhq.example.dto.BaseResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanhaiqiu
 * @date 2019/6/25
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

    @RequestMapping(value = "detail/{id}", method = {RequestMethod.GET})
    public BaseResponse detail(@PathVariable("id") Integer id) {
        return BaseResponse.success(id);
    }
}
