package com.fanhq.example.config;

import com.fanhq.example.core.Listener;
import com.fanhq.example.core.Processor;
import com.fanhq.example.core.Reader;
import com.fanhq.example.core.Writer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fanhaiqiu
 * @date 2019/6/17
 */
@Configuration
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job processJob() {
        return jobBuilderFactory.get("processJob")
                .incrementer(new RunIdIncrementer()).listener(listener())
                .flow(step()).end().build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step").<String, String>chunk(1)
                .reader(new Reader()).processor(new Processor())
                .writer(new Writer()).build();
    }

    @Bean
    public JobExecutionListener listener() {
        return new Listener();
    }

}