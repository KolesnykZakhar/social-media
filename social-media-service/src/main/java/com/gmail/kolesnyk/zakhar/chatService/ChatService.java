package com.gmail.kolesnyk.zakhar.chatService;

import com.gmail.kolesnyk.zakhar.chatService.chat.Chat;

public interface ChatService  {
    Chat getChatByUsers(int idUser, int idFriend);

    void saveMessage(String textMessage, int idUser, int idInterlocutor);
}
