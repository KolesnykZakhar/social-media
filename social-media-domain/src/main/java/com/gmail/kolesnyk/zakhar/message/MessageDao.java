package com.gmail.kolesnyk.zakhar.message;

import com.gmail.kolesnyk.zakhar.BaseDao;
import com.gmail.kolesnyk.zakhar.user.User;

import java.util.List;
import java.util.Set;

public interface MessageDao extends BaseDao<Message, Integer> {
    List<Message> getFullChat(Integer idUser, Integer idFriend);

    Integer amountMessagesByUsers(Integer idUser, Integer idFriend);

    List<Message> getShortChat(int idUser, int idFriend, int size);

    Set<User> getInterlocutors(int idUser);

    Message getLastMessage(int idUser, int idInterlocutor);

    Integer amountUnreadMessagesByUsers(int idUser, int idInterlocutor);

    Integer amountUnreadMessages(int idUser);

    void markMessagesAsReadByUsers(int idUser, int idInterlocutor);
}
