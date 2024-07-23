package com.example.modemate.DTO;


import lombok.Getter;

import java.util.List;

@Getter
public class ChatRoomDetailDTO {
    private String roomId;

    private String user;

    private String opponentUser;

    private List<ChatHistoryResponse> histories;

    public ChatRoomDetailDTO(String roomId, String user, String opponentUser, List<ChatHistoryResponse> histories) {
        this.roomId = roomId;
        this.user = user;
        this.opponentUser = opponentUser;
        this.histories = histories;
    }
}