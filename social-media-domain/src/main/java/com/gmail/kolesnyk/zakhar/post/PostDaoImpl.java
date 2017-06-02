package com.gmail.kolesnyk.zakhar.post;

import com.gmail.kolesnyk.zakhar.AbstractDao;
import com.gmail.kolesnyk.zakhar.user.User;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

import static com.gmail.kolesnyk.zakhar.user.VISIBILITY.PUBLIC;

/**
 * class implements and extends methods that need to ORM relations with {@link Post} class
 */
@Repository
public class PostDaoImpl extends AbstractDao<Post, Integer> implements PostDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> postsSublistByIdUser(Integer idUser, int offset, int amount) {
        return sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM posts WHERE id_user = :idUser ORDER BY date_post DESC LIMIT :offset, :amount ")
                .addEntity(Post.class).setParameter("idUser", idUser).setParameter("offset", offset).setParameter("amount", amount).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> listByUser(User user) {
        return sessionFactory.getCurrentSession().createCriteria(Post.class).add(Restrictions.eq("user", user)).addOrder(Order.desc("datePost")).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> listSearchString(String search) {
        return sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM posts WHERE text_post LIKE :search")
                .addEntity(Post.class).setParameter("search", "%" + search + "%").list();
    }

    @Override
    public Integer amountPostsByIdUser(int idUser) {
        return ((BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT count(*) FROM posts WHERE id_user = :idUser")
                .setParameter("idUser", idUser).uniqueResult()).intValue();
    }

    @Override
    public void addBookmark(int idUser, int idPost) {
        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO bookmarks (id_user, id_post) VALUES  ( :idUser, :idPost)")
                .setParameter("idUser", idUser).setParameter("idPost", idPost).executeUpdate();
    }

    @Override
    public Boolean isExistBookmark(int idUser, int idPost) {
        return ((BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT count(*) FROM bookmarks WHERE id_user = :idUser AND id_post = :idPost")
                .setParameter("idUser", idUser).setParameter("idPost", idPost).uniqueResult()).intValue() == 1;
    }

    @Override
    public void deleteBookmark(int idUser, int idPost) {
        sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM bookmarks WHERE id_user = :idUser AND id_post = :idPost")
                .setParameter("idUser", idUser).setParameter("idPost", idPost).executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> sublistBookmarksByUser(int idUser, int offset, int amount) {
        return sessionFactory.getCurrentSession().createSQLQuery("SELECT posJoinBookm.* FROM\n" +
                "  (SELECT posts.* FROM posts JOIN bookmarks ON posts.id_post = bookmarks.id_post WHERE bookmarks.id_user = :idUser) AS posJoinBookm\n" +
                "  JOIN\n" +
                "  ((SELECT DISTINCT users.* FROM users JOIN friends ON users.id_user = friends.id_friend WHERE friends.id_user = :idUser)\n" +
                "  UNION\n" +
                "  (SELECT users.* FROM users WHERE id_user = :idUser OR users.visibility = :visibility))\n" +
                "AS usr ON posJoinBookm.id_user = usr.id_user ORDER BY date_post DESC LIMIT :offset, :amount")
                .addEntity(Post.class).setParameter("idUser", idUser).setParameter("offset", offset).setParameter("amount", amount).setParameter("visibility", PUBLIC.ordinal()).list();
    }

    @Override
    public Integer amountBookmarksByIdUser(int idUser) {
        return ((BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT count(*) FROM\n" +
                "  (SELECT posts.* FROM posts JOIN bookmarks ON posts.id_post = bookmarks.id_post WHERE bookmarks.id_user = :idUser) AS posJoinBookm\n" +
                "  JOIN\n" +
                "  ((SELECT DISTINCT users.* FROM users JOIN friends ON users.id_user = friends.id_friend WHERE friends.id_user = :idUser)\n" +
                "  UNION\n" +
                "  (SELECT users.* FROM users WHERE id_user = :idUser OR users.visibility = :visibility))\n" +
                "AS usr ON posJoinBookm.id_user = usr.id_user")
                .setParameter("idUser", idUser).setParameter("visibility", PUBLIC.ordinal()).uniqueResult()).intValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> sublistNews(int idUser, int offset, int amount) {
//        return sessionFactory.getCurrentSession().createSQLQuery("(SELECT posts.* FROM posts JOIN\n" +
//                "  (SELECT * FROM\n" +
//                "    (SELECT users.id_user, friends.id_friend FROM users JOIN friends ON users.id_user = friends.id_user)\n" +
//                "      AS join_u_f WHERE join_u_f.id_friend = :idUser)\n" +
//                "        AS home WHERE (posts.id_user = home.id_user OR posts.id_user = home.id_friend))\n" +
//                "  UNION\n" +
//                "SELECT posts.* FROM posts JOIN\n" +
//                "  (SELECT id_user FROM users WHERE visibility = :visibility) AS visible ON posts.id_user =  visible.id_user\n" +
//                "ORDER BY date_post DESC LIMIT :offset, :amount")
        return sessionFactory.getCurrentSession().createSQLQuery("SELECT posts.* FROM posts\n" +
                "  JOIN\n" +
                "  ((SELECT DISTINCT users.* FROM users JOIN friends ON users.id_user = friends.id_friend WHERE friends.id_user = :idUser)\n" +
                "  UNION\n" +
                "  (SELECT users.* FROM users WHERE id_user = :idUser OR users.visibility = :visibility))\n" +
                "AS usr ON posts.id_user = usr.id_user ORDER BY date_post DESC LIMIT :offset, :amount")
                .addEntity(Post.class).setParameter("idUser", idUser).setParameter("offset", offset).setParameter("amount", amount).setParameter("visibility", PUBLIC.ordinal()).list();
    }

    @Override
    public Integer amountNewsByIdUser(int idUser) {
//        return ((BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT count(*) FROM ((SELECT posts.* FROM posts JOIN\n" +
//                "  (SELECT * FROM\n" +
//                "    (SELECT users.id_user, friends.id_friend FROM users JOIN friends ON users.id_user = friends.id_user)\n" +
//                "      AS join_u_f WHERE join_u_f.id_friend = :idUser)\n" +
//                "        AS home WHERE (posts.id_user = home.id_user OR posts.id_user = home.id_friend))\n" +
//                "  UNION\n" +
//                "SELECT posts.* FROM posts JOIN\n" +
//                "  (SELECT id_user FROM users WHERE visibility = :visibility) AS visible ON posts.id_user =  visible.id_user) AS result")
        return ((BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT count(*) FROM posts\n" +
                "  JOIN\n" +
                "  ((SELECT DISTINCT users.* FROM users JOIN friends ON users.id_user = friends.id_friend WHERE friends.id_user = :idUser)\n" +
                "  UNION\n" +
                "  (SELECT users.* FROM users WHERE id_user = :idUser OR users.visibility = :visibility))\n" +
                "AS usr ON posts.id_user = usr.id_user")
                .setParameter("idUser", idUser).setParameter("visibility", PUBLIC.ordinal()).uniqueResult()).intValue();
    }
}
