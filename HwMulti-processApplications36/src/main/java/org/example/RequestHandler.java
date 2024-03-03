package org.example;

import java.io.IOException;

import java.net.Socket;

import java.io.InputStream;
import java.io.OutputStream;


public class RequestHandler implements Runnable {
    private Socket clientSocket;
    private LoadBalancer loadBalancer;

    public RequestHandler(Socket clientSocket, LoadBalancer loadBalancer) {
        this.clientSocket = clientSocket;
        this.loadBalancer = loadBalancer;
    }

    @Override
    public void run() {
        try {
            // Получение запроса от клиента
            InputStream inputStream = clientSocket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String request = new String(buffer, 0, bytesRead);

            // Обработка запроса через Load Balancer
            String response = loadBalancer.processRequest(request);

            // Отправка ответа клиенту
            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write(response.getBytes());

            // Закрытие потоков и сокета
            inputStream.close();
            outputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}