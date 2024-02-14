package org.example;

import io.grpc.stub.StreamObserver;

public class NodeServiceImpl extends LoadBalancerServiceGrpc.LoadBalancerServiceImplBase {
    private final String name;

    public NodeServiceImpl(String name) {
        this.name = name;
    }

    @Override
    public void send(LoadBalancerRequest request, StreamObserver<LoadBalancerResponse> responseObserver) {
        LoadBalancerResponse response = LoadBalancerResponse.newBuilder()
                .setResponse("=".repeat(30) + "\n" +
                        "Thread name=" + Thread.currentThread().getName() + "\n" +
                        "Node name=" + name + "\n" +
                        "Message=" + request.getRequest() + "\n" +
                        "=".repeat(30))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
