package org.example;

import java.util.ArrayList;
import java.util.List;

public class LoadBalancer {
    private List<Node> nodes = new ArrayList<>();
    private int currentNodeIndex = 0;

    // Метод для добавления ноды
    public void addNode(Node node) {
        nodes.add(node);
    }

    // Метод для обработки запросов
    public synchronized String processRequest(String request) {
        if (nodes.isEmpty()) {
            return "No nodes available";
        }

        // Чередование вызовов нод
        Node node = nodes.get(currentNodeIndex);
        currentNodeIndex = (currentNodeIndex + 1) % nodes.size();

        // Попытка обработки запроса и возврат результата
        try {
            return node.handleRequest(request);
        } catch (Exception e) {
            return "Node " + node + " is not responding";
        }
    }
}
