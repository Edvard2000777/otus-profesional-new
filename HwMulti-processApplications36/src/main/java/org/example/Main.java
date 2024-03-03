package org.example;

public class Main {
    public static void main(String[] args){
        LoadBalancer loadBalancer = new LoadBalancer();
        loadBalancer.addNode(new Node(1));
        loadBalancer.addNode(new Node(2));

        LoadBalancerServer loadBalancerServer = new LoadBalancerServer(8080, loadBalancer);
        new Thread(loadBalancerServer).start();
    }
}
