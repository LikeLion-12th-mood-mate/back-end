package com.example.modemate.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ChatHistoryResponse {
    private String sender;
    private String message;
    private int readCount;
    private LocalDateTime createdAt;

    public ChatHistoryResponse(String sender, int readCount, String message, LocalDateTime createdAt) {
        this.sender = sender;
        this.message = message;
        this.readCount = readCount;
        this.createdAt = createdAt;
    }
}