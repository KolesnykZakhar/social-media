package com.gmail.kolesnyk.zakhar.message;

import com.gmail.kolesnyk.zakhar.BaseDao;

import java.util.List;

public interface MessageDao extends BaseDao<Message, Integer> {
    List<Message> getChat(Integer idUser, Integer idFriend);
}
