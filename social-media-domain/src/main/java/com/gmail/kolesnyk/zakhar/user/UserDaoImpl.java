package com.gmail.kolesnyk.zakhar.user;

import com.gmail.kolesnyk.zakhar.AbstractDao;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

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
        return ((BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT count(*) FROM friends WHERE id_user = :idUser")
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
    public void saveHashForEmail(String hashForEmail, Integer idUser) {
        sessionFactory.getCurrentSession().createSQLQuery
                ("INSERT INTO confirm_emails (id_user, hashed_email) VALUES (:idUser , :hashedEmail );")
                .setParameter("idUser", idUser).setParameter("hashedEmail", hashForEmail).executeUpdate();
    }

    @Override
    public Boolean removeUserByHashForEmail(String hashForEmail) {
        return sessionFactory.getCurrentSession().createSQLQuery
                ("DELETE FROM users WHERE id_user IN (SELECT confirm_emails.id_user FROM confirm_emails WHERE hashed_email = :hashedEmail);")
                .setParameter("hashedEmail", hashForEmail).executeUpdate() != 0;
    }

    @Override
    public User byHashForEmail(String hashForEmail) {
        return (User) sessionFactory.getCurrentSession().createSQLQuery
                ("SELECT * FROM users WHERE id_user IN (SELECT confirm_emails.id_user FROM confirm_emails WHERE hashed_email = :hashedEmail)")
                .addEntity(User.class).setParameter("hashedEmail", hashForEmail).uniqueResult();
    }

    @Override
    public Boolean removeHashForEmail(String hashForEmail) {
        return sessionFactory.getCurrentSession().createSQLQuery
                ("DELETE FROM confirm_emails WHERE hashed_email = :hashedEmail ;")
                .setParameter("hashedEmail", hashForEmail).executeUpdate() != 0;
    }

    @Override
    public Boolean saveHashForPassword(String hashForPassword, Integer idUser) {
        return sessionFactory.getCurrentSession().createSQLQuery
                ("INSERT INTO restore_password (id_user, hashed_password) VALUES (:idUser , :hashForPassword );")
                .setParameter("idUser", idUser).setParameter("hashForPassword", hashForPassword).executeUpdate() != 0;
    }

    @Override
    public Boolean removeRestorePassword(String hashForPassword) {
        return sessionFactory.getCurrentSession().createSQLQuery
                ("DELETE FROM restore_password WHERE restore_password.hashed_password = :hashForPassword ;")
                .setParameter("hashForPassword", hashForPassword).executeUpdate() != 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> searchByName(String search) {
        String[] split = search.split(" ");
        String firstName = split[0].trim().toLowerCase();
        String lastName = "";
        if (split.length > 1) {
            lastName = split[1].trim().toLowerCase();
        }
        return sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM users WHERE (first_name LIKE lower(:firstName) AND last_name LIKE lower(:lastName)) OR (first_name LIKE lower(:lastName) AND last_name LIKE lower(:firstName));")
                .addEntity(User.class).setParameter("firstName", "%" + firstName + "%").setParameter("lastName", "%" + lastName + "%").list();
    }

    @Override
    public boolean isFriends(int idCurrentUser, int idUser) {
        return ((BigInteger) sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT count(*) FROM friends WHERE (id_user = :idUser AND id_friend = :idFriend) OR (id_user = :idFriend AND id_friend = :idUser)")
                .setParameter("idUser", idCurrentUser).setParameter("idFriend", idUser).uniqueResult()).intValue() == 2;
    }

    @Override
    public void addFriend(int idCurrentUser, int idUser) {
        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO friends (id_user, id_friend) VALUES (:idCurrentUser, :idUser)")
                .setParameter("idCurrentUser", idCurrentUser).setParameter("idUser", idUser).executeUpdate();
        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO friends (id_user, id_friend) VALUES (:idUser, :idCurrentUser)")
                .setParameter("idCurrentUser", idCurrentUser).setParameter("idUser", idUser).executeUpdate();
        removeInvitationForFriendship(idCurrentUser, idUser);
    }

    @Override
    public void removeFriendship(int idCurrentUser, int idUser) {
        sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM friends WHERE (id_user = :idCurrentUser AND id_friend = :idUser) OR (id_user = :idUser AND id_friend = :idCurrentUser)")
                .setParameter("idCurrentUser", idCurrentUser).setParameter("idUser", idUser).executeUpdate();
    }

    @Override
    public boolean isInvitedForFriendship(int idCurrentUser, int idUser) {
        return ((BigInteger) sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT count(*) FROM inviting_for_friendship WHERE id_user = :idCurrentUser AND id_friend = :idUser")
                .setParameter("idCurrentUser", idCurrentUser).setParameter("idUser", idUser).uniqueResult()).intValue() == 1;
    }

    @Override
    public void inviteForFriendship(int idCurrentUser, int idUser) {
        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO inviting_for_friendship (id_user, id_friend) VALUES (:idCurrentUser, :idUser)")
                .setParameter("idCurrentUser", idCurrentUser).setParameter("idUser", idUser).executeUpdate();
    }

    @Override
    public Integer amountOfInvitations(int idUser) {
        return ((BigInteger) sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT count(*) FROM inviting_for_friendship WHERE id_friend = :idUser")
                .setParameter("idUser", idUser).uniqueResult()).intValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listInvitationsForFriendship(int idUser) {
        return sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT * FROM users WHERE id_user IN (SELECT inviting_for_friendship.id_user FROM inviting_for_friendship WHERE id_friend = :idUser)")
                .addEntity(User.class).setParameter("idUser", idUser).list();
    }

    @Override
    public void removeInvitationForFriendship(int idCurrentUser, int idUser) {
        sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM inviting_for_friendship WHERE (id_user = :idCurrentUser AND id_friend = :idUser) OR (id_user = :idUser AND id_friend = :idCurrentUser)")
                .setParameter("idCurrentUser", idCurrentUser).setParameter("idUser", idUser).executeUpdate();
    }
}
