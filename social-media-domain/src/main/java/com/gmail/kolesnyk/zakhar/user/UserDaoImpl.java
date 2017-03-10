package com.gmail.kolesnyk.zakhar.user;

import com.gmail.kolesnyk.zakhar.AbstractDao;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.List;

/**
 * class implements and extends methods that need to ORM relations with {@link User} class
 */
public class UserDaoImpl extends AbstractDao<User, Integer> implements UserDao {

    @Override
    public User selectByPhone(String phone) {
        return (User) session.createCriteria(User.class).add(Restrictions.eq("phone", phone)).uniqueResult();
    }

    @Override
    public User selectByEmail(String email) {
        return (User) session.createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
    }

    @Override
    public User selectByLogin(String login) {
        return (User) session.createCriteria(User.class).add(Restrictions.eq("login", login)).uniqueResult();
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<User> friendList(Integer idUser) {
        return session.createSQLQuery("SELECT * FROM users WHERE id_user IN (SELECT id_friend FROM friends WHERE id_user = :idUser)")
                .addEntity(User.class).setParameter("idUser", idUser).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public int amountFriends(Integer idUser) {
        return ((BigInteger) session.createSQLQuery("SELECT COUNT(*) FROM friends WHERE id_user = :idUser")
                .setParameter("idUser", idUser).uniqueResult()).intValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> friendListByRange(Integer idUser, int offset, int amount) {
        return session.createSQLQuery
                ("SELECT * FROM users WHERE id_user IN (SELECT id_friend FROM friends WHERE id_user = :idUser) ORDER BY id_user ASC LIMIT :offset, :amount ")
                .addEntity(User.class).setParameter("idUser", idUser).setParameter("offset", offset).setParameter("amount", amount).list();
    }
}
