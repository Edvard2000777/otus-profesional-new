package org.example;

public class Node {
    private int id;

    public Node(int id) {
        this.id = id;
    }

    // Метод обработки запроса
    public String handleRequest(String request) {
        return "Response from Node " + id + ": " + request;
    }
}