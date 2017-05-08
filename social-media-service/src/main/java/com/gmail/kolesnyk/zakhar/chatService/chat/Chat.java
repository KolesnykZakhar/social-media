package com.gmail.kolesnyk.zakhar.chatService.chat;


import com.gmail.kolesnyk.zakhar.message.Message;
import com.gmail.kolesnyk.zakhar.user.User;

import java.util.List;

public class Chat {
    private List<Message> messages;
    private int idUser;
    private User interlocutor;

    public Chat(List<Message> messages, int idUser, User interlocutorName) {
        this.messages = messages;
        this.idUser = idUser;
        this.interlocutor = interlocutorName;
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
