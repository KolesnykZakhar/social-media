package com.gmail.kolesnyk.zakhar.chatService;

import com.gmail.kolesnyk.zakhar.AbstractService;
import com.gmail.kolesnyk.zakhar.chatService.chat.Chat;
import com.gmail.kolesnyk.zakhar.message.Message;
import com.gmail.kolesnyk.zakhar.message.MessageDao;
import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatServiceImpl extends AbstractService implements ChatService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserDao userDao;

    public ChatServiceImpl(@Autowired Environment environment) {
        super(environment);
    }

    @Override
    @Transactional(readOnly = true)
    public Chat getChatByUsers(int idUser, int idFriend) {
        List<Message> messages = messageDao.getChat(idUser, idFriend);
        User interlocutor = userDao.selectById(idFriend);
        return new Chat(messages, idUser, interlocutor);
    }

    @Override
    @Transactional
    public void saveMessage(String textMessage, int idUser, int idInterlocutor) {
        Message message = new Message();
        message.setTextMessage(textMessage);
        message.setIdUser(idUser);
        message.setIdFriend(idInterlocutor);
        messageDao.save(message);
    }
}
