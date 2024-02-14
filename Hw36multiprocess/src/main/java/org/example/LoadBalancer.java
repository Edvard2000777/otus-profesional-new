package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LoadBalancer {
    private final int LOAD_BALANCER_PORT = 8080;

    public LoadBalancer(CopyOnWriteArrayList<Integer> portList) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(LOAD_BALANCER_PORT)
                .addService(new LoadBalancerServiceImpl(portList)).build();
        server.start();
        server.awaitTermination();
    }
}
