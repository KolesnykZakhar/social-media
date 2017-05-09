package com.gmail.kolesnyk.zakhar.message;


import com.gmail.kolesnyk.zakhar.AbstractDao;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
public class MessageDaoImpl extends AbstractDao<Message, Integer> implements MessageDao {
    @Override
    @SuppressWarnings("unchecked")
    public List<Message> getFullChat(Integer idUser, Integer idFriend) {
        return sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM messages WHERE (id_user = :idUser AND id_friend = :idFriend) OR (id_user = :idFriend AND id_friend = :idUser) ORDER BY id_message DESC")
                .addEntity(Message.class).setParameter("idUser", idUser).setParameter("idFriend", idFriend).list();
    }

    @Override
    public Integer amountMessagesByUsers(Integer idUser, Integer idFriend) {
        return ((BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT count(*) FROM messages WHERE (id_user = :idUser AND id_friend = :idFriend) OR (id_user = :idFriend AND id_friend = :idUser);")
                .setParameter("idUser", idUser).setParameter("idFriend", idFriend).uniqueResult()).intValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Message> getShortChat(int idUser, int idFriend, int size) {
        return sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM messages WHERE (id_user = :idUser AND id_friend = :idFriend) OR (id_user = :idFriend AND id_friend = :idUser) ORDER BY id_message DESC LIMIT 0, :size ;")
                .addEntity(Message.class).setParameter("idUser", idUser).setParameter("idFriend", idFriend).setParameter("size", size).list();
    }
}
