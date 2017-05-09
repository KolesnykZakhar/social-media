package com.gmail.kolesnyk.zakhar.chatService.chat;

import java.util.Set;

public class ChatsMenu {
    private int idUser;
    private Set<ChatHeader> chatHeaders;
    private int amountUnread;

    public ChatsMenu(int idUser, Set<ChatHeader> chatHeaders, int amountUnread) {
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

    public Set<ChatHeader> getChatHeaders() {
        return chatHeaders;
    }

    public void setChatHeaders(Set<ChatHeader> chatHeaders) {
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
