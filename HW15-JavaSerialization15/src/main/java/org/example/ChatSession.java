package org.example;

import java.util.List;

public class ChatSession {
    public String chat_id;
    public String chat_identifier;
    public String display_name;
    public int is_deleted;
    public ChatMember members;
    public List<ChatMessage> messages;

    @Override
    public String toString() {
        return "ChatSession{" +
                "chat_id='" + chat_id + '\'' +
                ", chat_identifier='" + chat_identifier + '\'' +
                ", display_name='" + display_name + '\'' +
                ", is_deleted=" + is_deleted +
                ", members=" + members +
                ", messages=" + messages +
                '}';
    }
}
