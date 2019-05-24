package com.fanhq.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanhaiqiu
 * @date 2019/5/24
 */
@RequestMapping("open")
@RestController
public class OpenController {

    @RequestMapping("index")
    public String index() {
        return "hello world";
    }
}
