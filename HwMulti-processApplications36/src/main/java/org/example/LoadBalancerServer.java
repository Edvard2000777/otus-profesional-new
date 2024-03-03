package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LoadBalancerServer implements Runnable {
    private LoadBalancer loadBalancer;
    private int port;

    public LoadBalancerServer(int port, LoadBalancer loadBalancer) {
        this.port = port;
        this.loadBalancer = loadBalancer;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Load Balancer is running on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new RequestHandler(clientSocket, loadBalancer)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
