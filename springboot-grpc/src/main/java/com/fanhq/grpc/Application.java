package com.fanhq.grpc;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * @author fanhaiqiu
 * @date 2019/3/7
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);


    @Autowired
    private List<BindableService> servers;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (CollectionUtils.isEmpty(servers)) {
            logger.info("No Server");
            return;
        }
        ServerBuilder builder = ServerBuilder.forPort(50051);
        servers.forEach(s -> {
            builder.addService(s);
        });
        Server server = builder.build().start();
        logger.info("Server started, listening on " + 50051);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            if (server != null) {
                server.shutdown();
            }
        }));
        if (server != null) {
            //单元测试时关闭阻塞
            server.awaitTermination();
        }
    }

}
