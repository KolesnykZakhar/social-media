package com.gmail.kolesnyk.zakhar.chatService;

import com.gmail.kolesnyk.zakhar.AbstractService;
import com.gmail.kolesnyk.zakhar.chatService.chat.Chat;
import com.gmail.kolesnyk.zakhar.message.Message;
import com.gmail.kolesnyk.zakhar.message.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.List;


public class ChatServiceImpl extends AbstractService implements ChatService {

    @Autowired
    private MessageDao messageDao;

    public ChatServiceImpl(@Autowired Environment environment) {
        super(environment);
    }

    @Override
    public Chat getChatByUsers(int idUser, int idFriend) {
        List<Message> messages = messageDao.getChat(idUser, idFriend);

    }
}
