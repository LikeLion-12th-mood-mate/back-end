package com.example.modemate.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class ChatHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;

    private String message;

    private int readCount = 2;

    private LocalDateTime createdAt;
    public ChatHistory(ChatRoom chatRoom, User sender, String message, LocalDateTime createdAt) {
        this.chatRoom = chatRoom;
        this.sender = sender;
        this.message = message;
        this.createdAt = createdAt;
    }
    public static ChatHistory create(ChatRoom chatRoom, User sender, String message, LocalDateTime createdAt) {
        ChatHistory chatHistory = new ChatHistory(chatRoom, sender, message, createdAt);
        chatRoom.getHistories().add(chatHistory);
        return chatHistory;
    }
}