package com.fanhq.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author fanhaiqiu
 */

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class BatchApplication {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job processJob;

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }

    @Scheduled(fixedRate = 10000)
    public void run() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(processJob, jobParameters);
    }
}
