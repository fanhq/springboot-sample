package com.fanhq.example.core;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * @author fanhaiqiu
 * @date 2019/6/17
 */
public class Reader implements ItemReader<String> {

    private String[] messages = {"javainuse.com",
            "Welcome to Spring Batch Example",
            "We use mysql Database for this example"};

    private int count = 0;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (count < messages.length) {
            return messages[count++];
        }
        count = 0;
        return null;
    }

}
