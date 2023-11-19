package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DataSerializer {
    private final Gson gson = new Gson();



    @SuppressWarnings("unchecked")
    public List<ChatSession> deserializeFromJson(String filePath) {
        try {
            FileReader reader = new FileReader(filePath);
            return ((Map<String, List<ChatSession>>)gson.fromJson(reader, new TypeToken<Map<String, Object>>() {
            }.getType())).get("chat_sessions");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void serializeToJson(List<ChatMessage> processedData, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            Gson gson = new Gson();
            gson.toJson(processedData, writer);
        } catch (IOException e) {
            e.printStackTrace();
            // Обработка ошибок записи в файл
        }
    }
}