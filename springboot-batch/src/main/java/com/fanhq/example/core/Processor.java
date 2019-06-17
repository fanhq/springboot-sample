package com.fanhq.example.core;

import org.springframework.batch.item.ItemProcessor;

/**
 * @author fanhaiqiu
 * @date 2019/6/17
 */
public class Processor implements ItemProcessor<String, String> {

    @Override
    public String process(String data) throws Exception {
        return data.toUpperCase();
    }

}
