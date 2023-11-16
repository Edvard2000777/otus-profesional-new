package org.example;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Чтение данных из JSON файла
        DataSerializer serializer = new DataSerializer();
        List<ChatSession> data = serializer.deserializeFromJson("sms-348536-95ecd0 (1).json");
        System.out.println(data);
        // Обработка данных
        DataProcessor processor = new DataProcessor();
     //   List<ChatMessage> processedData = processor.groupAndSortMessages(data);
//
//        // Сериализация данных в JSON
//        serializer.serializeToJson(processedData, "output.json");
//
//        // Вывод данных на консоль
//        for (ChatMessage message : processedData) {
//            System.out.println("Belong Number: " + message.belong_number);
//            System.out.println("Send Date: " + message.send_date);
//            System.out.println("Text: " + message.text);
//            // Вывод других полей, если они есть
//        }
    }
}