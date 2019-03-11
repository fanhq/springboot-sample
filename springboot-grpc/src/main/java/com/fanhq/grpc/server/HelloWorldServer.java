package com.fanhq.grpc.server;

import com.fanhq.grpc.service.GreeterImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author fanhaiqiu
 * @date 2019/3/8
 */

@Component
public class HelloWorldServer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldServer.class);

    @Autowired
    private GreeterImpl greeter;

    private Server server;

    private void start() throws IOException {
        /* The port on which the server should run */
        int port = 50051;
        this.server = ServerBuilder.forPort(port)
                .addService(greeter)
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            this.stop();
        }));
    }

    private void stop() {
        if (this.server != null) {
            this.server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (this.server != null) {
            this.server.awaitTermination();
        }
    }


    @Override
    public void run(String... args) throws Exception {
        this.start();
        this.blockUntilShutdown();
    }

}
