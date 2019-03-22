package com.fanhq.grpc.server;

import com.fanhq.genera.GreeterGrpc;
import com.fanhq.genera.HelloReply;
import com.fanhq.genera.HelloRequest;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;

/**
 * @author fanhaiqiu
 * @date 2019/3/8
 */

@Component
public class HelloWorldServer extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
