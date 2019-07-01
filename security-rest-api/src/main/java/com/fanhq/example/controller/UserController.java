package com.fanhq.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanhaiqiu
 * @date 2019/7/1
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

    @GetMapping(value = "hi")
    public String sayHi(){
        return "hello world";
    }
}
