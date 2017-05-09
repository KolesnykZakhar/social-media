package com.gmail.kolesnyk.zakhar.chatService;

import com.gmail.kolesnyk.zakhar.chatService.chat.Chat;
import org.springframework.transaction.annotation.Transactional;

public interface ChatService  {
    Chat getFullChatByUsers(int idUser, int idFriend);

    @Transactional(readOnly = true)
    Chat getShortChatByUsers(int idUser, int idFriend);

    void saveMessage(String textMessage, int idUser, int idInterlocutor);
}
