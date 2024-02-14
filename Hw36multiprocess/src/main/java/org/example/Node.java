package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class Node {

    public Node(String nameNode, int port) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(port)
                .addService(new NodeServiceImpl(nameNode)).build();
        server.start();
        server.awaitTermination();
    }
}
