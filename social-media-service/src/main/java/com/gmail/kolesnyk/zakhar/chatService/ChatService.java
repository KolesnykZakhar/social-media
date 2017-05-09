package com.gmail.kolesnyk.zakhar.chatService;

import com.gmail.kolesnyk.zakhar.chatService.chat.Chat;
import com.gmail.kolesnyk.zakhar.chatService.chat.ChatsMenu;

public interface ChatService {
    Chat getFullChatByUsers(int idUser, int idFriend);

    Chat getShortChatByUsers(int idUser, int idFriend);

    void saveMessage(String textMessage, int idUser, int idInterlocutor);

    ChatsMenu getChatsMenu(int idUser);

    void markMessagesAsReadByUsers(int idUser, int idInterlocutor);

    Integer amountUnreadMessages(int idUser);
}
