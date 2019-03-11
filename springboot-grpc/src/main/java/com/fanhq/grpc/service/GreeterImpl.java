package com.fanhq.grpc.service;

import com.fanhq.genera.GreeterGrpc;
import com.fanhq.genera.HelloReply;
import com.fanhq.genera.HelloRequest;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

/**
 * @author fanhaiqiu
 * @date 2019/3/8
 */

@Service
public class GreeterImpl extends GreeterGrpc.GreeterImplBase {


    public GreeterImpl(){
        System.out.println("GreeterImpl");
    }

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}

