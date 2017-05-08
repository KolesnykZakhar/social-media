package com.gmail.kolesnyk.zakhar.message;


import com.gmail.kolesnyk.zakhar.AbstractDao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageDaoImpl extends AbstractDao<Message, Integer> implements MessageDao {
    @Override
    @SuppressWarnings("unchecked")
    public List<Message> getChat(Integer idUser, Integer idFriend) {
        return sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM messages WHERE (id_user = :idUser AND id_friend = :idFriend) OR (id_user = :idFriend AND id_friend = :idUser) ORDER BY id_message DESC")
                .addEntity(Message.class).setParameter("idUser", idUser).setParameter("idFriend", idFriend).list();
    }
}
