package com.gmail.kolesnyk.zakhar.user;

import com.gmail.kolesnyk.zakhar.AbstractDao;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * class implements and extends methods that need to ORM relations with {@link User} class
 */
@Component
public class UserDaoImpl extends AbstractDao<User, Integer> implements UserDao {

    @Override
    public User selectByPhone(String phone) {
        return (User) sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("phone", phone)).uniqueResult();
    }

    @Override
    public User selectByEmail(String email) {
        return (User) sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
    }

    @Override
    public User selectByLogin(String login) {
        return (User) sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("login", login)).uniqueResult();
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<User> friendList(Integer idUser) {
        return sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM users WHERE id_user IN (SELECT id_friend FROM friends WHERE id_user = :idUser)")
                .addEntity(User.class).setParameter("idUser", idUser).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public int amountFriends(Integer idUser) {
        return ((BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT COUNT(*) FROM friends WHERE id_user = :idUser")
                .setParameter("idUser", idUser).uniqueResult()).intValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> friendListByRange(Integer idUser, int offset, int amount) {
        return sessionFactory.getCurrentSession().createSQLQuery
                ("SELECT * FROM users WHERE id_user IN (SELECT id_friend FROM friends WHERE id_user = :idUser) ORDER BY id_user ASC LIMIT :offset, :amount ;")
                .addEntity(User.class).setParameter("idUser", idUser).setParameter("offset", offset).setParameter("amount", amount).list();
    }

    @Override
    public void saveHashedEmail(String hashedEmail, Integer idUser) {
//        System.out.println("has");
        sessionFactory.getCurrentSession().createSQLQuery
                ("INSERT INTO hashed_emails (id_user, hashed_email) VALUES (:idUser , :hashedEmail );")
                .setParameter("idUser", idUser).setParameter("hashedEmail", hashedEmail).executeUpdate();
//        ("INSERT INTO hashedemails (id_user, hashed_email) VALUES (1 , 'has' );").executeUpdate();
    }

    @Override
    public void removeUserByHashedEmail(String hashedEmail) {
        sessionFactory.getCurrentSession().createSQLQuery
        ("/*DELETE FROM hashed_emails WHERE hashed_email = :hashedEmail; */DELETE FROM users WHERE id_user IN (SELECT hashed_emails.id_user FROM hashed_emails WHERE hashed_email = :hashedEmail);")
                .setParameter("hashedEmail", hashedEmail)/*.setParameter("hashedEmail", hashedEmail)*/.executeUpdate();
    }
    //8f2aa03b53b32bd948b5b483f06e826b

    @Override
    public User byHashedEmail(String hashedEmail) {
        return (User)sessionFactory.getCurrentSession().createSQLQuery
                ("SELECT * FROM users WHERE id_user IN (SELECT hashed_emails.id_user FROM hashed_emails WHERE hashed_email = :hashedEmail)")
                .addEntity(User.class).setParameter("hashedEmail", hashedEmail).uniqueResult();
    }

    @Override
    public void removeHashedEmail(String hashedEmail) {
        sessionFactory.getCurrentSession().createSQLQuery
                ("DELETE FROM hashed_emails WHERE hashed_email = :hashedEmail ;")
                .setParameter("hashedEmail", hashedEmail).executeUpdate();
    }
}
