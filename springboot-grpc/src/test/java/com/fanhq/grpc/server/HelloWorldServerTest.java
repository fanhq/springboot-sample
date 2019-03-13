package com.fanhq.grpc.server;

import com.fanhq.genera.GreeterGrpc;
import com.fanhq.genera.HelloReply;
import com.fanhq.genera.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author fanhaiqiu
 * @date 2019/3/13
 */
public class HelloWorldServerTest {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldServer.class);

    @Test
    public void helloWorldTest() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 50051)
                .usePlaintext()
                .build();
        GreeterGrpc.GreeterBlockingStub blockingStub = GreeterGrpc.newBlockingStub(channel);
        //构造请求参数
        HelloRequest request = HelloRequest.newBuilder().setName("hello everyone").build();
        HelloReply response;
        try {
            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) {
            logger.error("RPC failed: {0}", e.getStatus());
            return;
        } finally {
            try {
                channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("Greeting: " + response.getMessage());
    }
}