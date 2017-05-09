package com.gmail.kolesnyk.zakhar.message;

import com.gmail.kolesnyk.zakhar.BaseDao;

import java.util.List;

public interface MessageDao extends BaseDao<Message, Integer> {
    List<Message> getFullChat(Integer idUser, Integer idFriend);

    Integer amountMessagesByUsers(Integer idUser, Integer idFriend);

    List<Message> getShortChat(int idUser, int idFriend, int size);
}
