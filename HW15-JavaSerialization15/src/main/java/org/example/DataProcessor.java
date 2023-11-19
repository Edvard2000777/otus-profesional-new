package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class DataProcessor {
//    public List<ChatMessage> groupAndSortMessages(List<ChatSession> chatSessions) {
//        List<ChatMessage> sortedMessages = new ArrayList<>();
//
//        Map<String, List<ChatMessage>> messageGroups = new HashMap<>();
//
//        for (ChatSession session : chatSessions) {
//            for (ChatMessage message : session.messages.belong_number) {
//                String belongNumber = message.belong_number;
//                List<ChatMessage> group = messageGroups.computeIfAbsent(belongNumber, k -> new ArrayList<>());
//                group.add(message);
//            }
//        }
//
//        for (List<ChatMessage> group : messageGroups.values()) {
//            group.sort(Comparator.comparing(m -> parseDate(m.send_date)));
//            sortedMessages.addAll(group);
//        }
//
//        return sortedMessages;
//    }

    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            // Обработка ошибки парсинга даты
            return null;
        }
    }

}