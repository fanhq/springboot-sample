package com.fanhq.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanhaiqiu
 * @date 2019/5/23
 */
@RestController
@RequestMapping("api")
public class RequestController {

    @RequestMapping("index")
    public String index(){
        return "hello world";
    }
}
