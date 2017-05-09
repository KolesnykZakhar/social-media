package com.gmail.kolesnyk.zakhar.message;


import com.gmail.kolesnyk.zakhar.AbstractDao;
import com.gmail.kolesnyk.zakhar.user.User;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MessageDaoImpl extends AbstractDao<Message, Integer> implements MessageDao {
    @Override
    @SuppressWarnings("unchecked")
    public List<Message> getFullChat(Integer idUser, Integer idFriend) {
        return sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM messages WHERE (id_sender = :idUser AND id_receiver = :idFriend) OR (id_sender = :idFriend AND id_receiver = :idUser) ORDER BY id_message DESC")
                .addEntity(Message.class).setParameter("idUser", idUser).setParameter("idFriend", idFriend).list();
    }

    @Override
    public Integer amountMessagesByUsers(Integer idUser, Integer idFriend) {
        return ((BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT count(*) FROM messages WHERE (id_sender = :idUser AND id_receiver = :idFriend) OR (id_sender = :idFriend AND id_receiver = :idUser);")
                .setParameter("idUser", idUser).setParameter("idFriend", idFriend).uniqueResult()).intValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Message> getShortChat(int idUser, int idFriend, int size) {
        return sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM messages WHERE (id_sender = :idUser AND id_receiver = :idFriend) OR (id_sender = :idFriend AND id_receiver = :idUser) ORDER BY id_message DESC LIMIT 0, :size ;")
                .addEntity(Message.class).setParameter("idUser", idUser).setParameter("idFriend", idFriend).setParameter("size", size).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<User> getInterlocutors(int idUser) {
        List<User> listInterlocutors = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM users WHERE id_user IN (SELECT id_sender FROM messages WHERE id_receiver = :idUser);").addEntity(User.class).setParameter("idUser", idUser).list();
        listInterlocutors.addAll(sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM users WHERE id_user IN (SELECT id_receiver FROM messages WHERE id_sender = :idUser);").addEntity(User.class).setParameter("idUser", idUser).list());
        return listInterlocutors.stream().collect(Collectors.toSet());
    }

    @Override
    public Message getLastMessage(int idUser, int idInterlocutor) {
        return (Message) sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM messages WHERE (id_sender = :idUser AND id_receiver = :idInterlocutor) OR (id_sender = :idInterlocutor AND id_receiver = :idUser) ORDER BY id_message DESC")
                .addEntity(Message.class).setParameter("idUser", idUser).setParameter("idInterlocutor", idInterlocutor).setMaxResults(1).uniqueResult();
    }

    @Override
    public Integer amountUnreadMessagesByUsers(int idUser, int idInterlocutor) {
        return ((BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT count(*) FROM messages WHERE id_sender = :idInterlocutor AND id_receiver = :idUser AND messages.unread = 1;")
                .setParameter("idUser", idUser).setParameter("idInterlocutor", idInterlocutor).uniqueResult()).intValue();
    }

    @Override
    public Integer amountUnreadMessages(int idUser) {
        return ((BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT count(*) FROM messages WHERE id_receiver = :idUser AND unread = 1;")
                .setParameter("idUser", idUser).uniqueResult()).intValue();
    }

    @Override
    public void markMessagesAsReadByUsers(int idUser, int idInterlocutor) {
        sessionFactory.getCurrentSession().createSQLQuery("UPDATE messages SET unread = 0 WHERE (id_sender = :idInterlocutor AND id_receiver = :idUser)")
                .setParameter("idUser", idUser).setParameter("idInterlocutor", idInterlocutor).executeUpdate();
    }
}
