package org.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LoadBalancerServiceImpl extends LoadBalancerServiceGrpc.LoadBalancerServiceImplBase {

    private int requestCount = 0;
    private final CopyOnWriteArrayList<Integer> portList;

    public LoadBalancerServiceImpl(CopyOnWriteArrayList<Integer> portList) {
        this.portList = portList;
    }

    @Override
    public void send(LoadBalancerRequest request, StreamObserver<LoadBalancerResponse> responseObserver) {
        int currentPort = portList.get(requestCount % portList.size());
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", currentPort)
                .usePlaintext()
                .build();
        LoadBalancerServiceGrpc.LoadBalancerServiceBlockingStub stub
                = LoadBalancerServiceGrpc.newBlockingStub(channel);
        LoadBalancerResponse proxyResponse = stub.send(LoadBalancerRequest.newBuilder()
                .setRequest(request.getRequest())
                .build());
        requestCount++;
        responseObserver.onNext(proxyResponse);
        responseObserver.onCompleted();
    }
}
