package com.gmail.kolesnyk.zakhar.chatService;

import com.gmail.kolesnyk.zakhar.AbstractService;
import com.gmail.kolesnyk.zakhar.chatService.chat.Chat;
import com.gmail.kolesnyk.zakhar.chatService.chat.ChatHeader;
import com.gmail.kolesnyk.zakhar.chatService.chat.ChatsMenu;
import com.gmail.kolesnyk.zakhar.message.Message;
import com.gmail.kolesnyk.zakhar.message.MessageDao;
import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.user.UserDao;
import com.gmail.kolesnyk.zakhar.userService.userActivityMap.UserActivityMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl extends AbstractService implements ChatService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserActivityMap userActivityMap;

    public ChatServiceImpl(@Autowired Environment environment) {
        super(environment);
    }

    @Override
    @Transactional(readOnly = true)
    public Chat getFullChatByUsers(int idUser, int idFriend) {
        List<Message> messages = messageDao.getFullChat(idUser, idFriend);
        User interlocutor = userDao.selectById(idFriend);
        return new Chat(messages, idUser, interlocutor);
    }

    @Override
    @Transactional(readOnly = true)
    public Chat getShortChatByUsers(int idUser, int idFriend) {
        List<Message> messages = messageDao.getShortChat(idUser, idFriend, SIZE_OF_SHORT_CHAT);
        User interlocutor = userDao.selectById(idFriend);
        int amountMessages = messageDao.amountMessagesByUsers(idUser, idFriend);
        return new Chat(messages, idUser, interlocutor, amountMessages);
    }

    @Override
    @Transactional
    public void saveMessage(String textMessage, int idUser, int idInterlocutor) {
        Message message = new Message();
        message.setTextMessage(textMessage);
        message.setIdUser(idUser);
        message.setIdFriend(idInterlocutor);
        message.setUnread(true);
        messageDao.save(message);
    }

    @Override
    @Transactional(readOnly = true)
    public ChatsMenu getChatsMenu(final int idUser) {
        final int[] mainAmountUnread = {0};
        Set<User> interlocutors = messageDao.getInterlocutors(idUser);
        Set<ChatHeader> chatHeaderSet = interlocutors.stream().map(user -> {
            if (userActivityMap.isOnline(user.getIdUser())) {
                user.setOnline(true);
            }
            Message lastMessage = messageDao.getLastMessage(idUser, user.getIdUser());
            int amountUnread = messageDao.amountUnreadMessagesByUsers(idUser, user.getIdUser());
            mainAmountUnread[0] += amountUnread;
            return new ChatHeader(lastMessage, amountUnread, user);
        }).collect(Collectors.toSet());
        return new ChatsMenu(idUser, chatHeaderSet, mainAmountUnread[0]);
    }

    @Override
    @Transactional
    public void markAsRead(int idMessage) {

    }
}
