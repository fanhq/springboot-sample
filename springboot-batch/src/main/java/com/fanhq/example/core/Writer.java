package com.fanhq.example.core;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * @author fanhaiqiu
 * @date 2019/6/17
 */
public class Writer implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> messages) throws Exception {
        for (String msg : messages) {
            System.out.println("Writing the data :" + msg);
        }
    }

}
