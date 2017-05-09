package com.gmail.kolesnyk.zakhar.chatService.chat;

import com.gmail.kolesnyk.zakhar.message.Message;
import com.gmail.kolesnyk.zakhar.user.User;

public class ChatHeader {
    private Message lastMessage;
    private int amountUnread;
    private User interlocutor;

    public ChatHeader(Message lastMessage, int amountUnread, User interlocutor) {
        this.lastMessage = lastMessage;
        this.amountUnread = amountUnread;
        this.interlocutor = interlocutor;
        String text = lastMessage.getTextMessage();
        if (text.length() > 50) {
            lastMessage.setTextMessage(text.substring(0, 50));
        }
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getAmountUnread() {
        return amountUnread;
    }

    public void setAmountUnread(int amountUnread) {
        this.amountUnread = amountUnread;
    }

    public User getInterlocutor() {
        return interlocutor;
    }

    public void setInterlocutor(User interlocutor) {
        this.interlocutor = interlocutor;
    }
    public boolean hasUnread() {
        return amountUnread > 0;
    }
}
