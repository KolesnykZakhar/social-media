package com.gmail.kolesnyk.zakhar.chatService.chat;


import com.gmail.kolesnyk.zakhar.message.Message;
import com.gmail.kolesnyk.zakhar.user.User;

import java.util.List;

/**
 * The {@code Chat} class used representing chat between two Users
 *
 * @author Kolesnyk Zakhar
 * @see com.gmail.kolesnyk.zakhar.chatService.chat.ChatHeader
 * @see com.gmail.kolesnyk.zakhar.chatService.chat.ChatsMenu
 * @see com.gmail.kolesnyk.zakhar.chatService.ChatService
 * @since JDK1.8
 */
public class Chat {

    private List<Message> messages;
    private int idUser;
    private User interlocutor;
    private int amountMessages;

    public Chat(List<Message> messages, int idUser, User interlocutorName) {
        this.messages = messages;
        this.idUser = idUser;
        this.interlocutor = interlocutorName;
        this.amountMessages = messages.size();
    }

    public Chat(List<Message> messages, int idUser, User interlocutor, int amountMessages) {
        this.messages = messages;
        this.idUser = idUser;
        this.interlocutor = interlocutor;
        this.amountMessages = amountMessages;
    }

    public int getAmountMessages() {
        return amountMessages;
    }

    public void setAmountMessages(int amountMessages) {
        this.amountMessages = amountMessages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public User getInterlocutor() {
        return interlocutor;
    }

    public void setInterlocutor(User interlocutor) {
        this.interlocutor = interlocutor;
    }
}
