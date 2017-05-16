package com.gmail.kolesnyk.zakhar.chatService.chat;

import java.util.List;

/**
 * The {@code Chat} class used representing chats menu between User and all it interlocutors
 *
 * @author Kolesnyk Zakhar
 * @see com.gmail.kolesnyk.zakhar.chatService.chat.ChatHeader
 * @see com.gmail.kolesnyk.zakhar.chatService.chat.Chat
 * @see com.gmail.kolesnyk.zakhar.chatService.ChatService
 * @since JDK1.8
 */
public class ChatsMenu {
    private int idUser;
    private List<ChatHeader> chatHeaders;
    private int amountUnread;

    public ChatsMenu(int idUser, List<ChatHeader> chatHeaders, int amountUnread) {
        this.idUser = idUser;
        this.chatHeaders = chatHeaders;
        this.amountUnread = amountUnread;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public List<ChatHeader> getChatHeaders() {
        return chatHeaders;
    }

    public void setChatHeaders(List<ChatHeader> chatHeaders) {
        this.chatHeaders = chatHeaders;
    }

    public int getAmountUnread() {
        return amountUnread;
    }

    public void setAmountUnread(int amountUnread) {
        this.amountUnread = amountUnread;
    }

    public boolean hasUnread() {
        return amountUnread > 0;
    }
}
