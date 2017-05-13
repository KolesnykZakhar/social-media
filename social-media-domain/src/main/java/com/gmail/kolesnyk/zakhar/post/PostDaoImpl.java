package com.gmail.kolesnyk.zakhar.post;

import com.gmail.kolesnyk.zakhar.AbstractDao;
import com.gmail.kolesnyk.zakhar.user.User;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

import static com.gmail.kolesnyk.zakhar.user.VISIBILITY.PUBLIC;

/**
 * class implements and extends methods that need to ORM relations with {@link Post} class
 */
@Component
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
    @SuppressWarnings("unchecked")
    public List<Post> sublistBookmarksByUser(int idUser, int offset, int amount) {
        return sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM ((SELECT posts.* FROM posts JOIN\n" +
                "  (SELECT * FROM\n" +
                "    (SELECT users.id_user, friends.id_friend FROM users JOIN friends ON users.id_user = friends.id_user)\n" +
                "      AS join_u_f WHERE join_u_f.id_friend = :idUser)\n" +
                "    AS home WHERE (posts.id_user = home.id_user OR posts.id_user = home.id_friend))\n" +
                "UNION\n" +
                "SELECT posts.* FROM posts JOIN\n" +
                "  (SELECT id_user FROM users WHERE visibility = :visibility) AS visible ON posts.id_user =  visible.id_user) AS news JOIN bookmarks ON news.id_post = bookmarks.id_user\n" +
                "ORDER BY date_post DESC LIMIT :offset, :amount")
                .addEntity(Post.class).setParameter("idUser",idUser).setParameter("offset",offset).setParameter("amount",amount).setParameter("visibility", PUBLIC.ordinal()).list();
//        return null;
    }

    @Override
    public Integer amountBookmarksByIdUser(int idUser) {
        return ((BigInteger)sessionFactory.getCurrentSession().createSQLQuery("SELECT count(*) FROM ((SELECT posts.* FROM posts JOIN\n" +
                "  (SELECT * FROM\n" +
                "    (SELECT users.id_user, friends.id_friend FROM users JOIN friends ON users.id_user = friends.id_user)\n" +
                "      AS join_u_f WHERE join_u_f.id_friend = :idUser)\n" +
                "    AS home WHERE (posts.id_user = home.id_user OR posts.id_user = home.id_friend))\n" +
                "               UNION\n" +
                "               SELECT posts.* FROM posts JOIN\n" +
                "                 (SELECT id_user FROM users WHERE visibility = :visibility) AS visible ON posts.id_user =  visible.id_user) AS news JOIN bookmarks ON news.id_post = bookmarks.id_user")
                .setParameter("idUser",idUser).setParameter("visibility", PUBLIC.ordinal()).uniqueResult()).intValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> sublistNews(int idUser, int offset, int amount) {
        return sessionFactory.getCurrentSession().createSQLQuery("(SELECT posts.* FROM posts JOIN\n" +
                "  (SELECT * FROM\n" +
                "    (SELECT users.id_user, friends.id_friend FROM users JOIN friends ON users.id_user = friends.id_user)\n" +
                "      AS join_u_f WHERE join_u_f.id_friend = :idUser)\n" +
                "        AS home WHERE (posts.id_user = home.id_user OR posts.id_user = home.id_friend))\n" +
                "  UNION\n" +
                "SELECT posts.* FROM posts JOIN\n" +
                "  (SELECT id_user FROM users WHERE visibility = :visibility) AS visible ON posts.id_user =  visible.id_user\n" +
                "ORDER BY date_post DESC LIMIT :offset, :amount")
                .addEntity(Post.class).setParameter("idUser", idUser).setParameter("offset", offset).setParameter("amount", amount).setParameter("visibility", PUBLIC.ordinal()).list();
    }

    @Override
    public Integer amountNewsByIdUser(int idUser) {
        return ((BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT count(*) FROM ((SELECT posts.* FROM posts JOIN\n" +
                "  (SELECT * FROM\n" +
                "    (SELECT users.id_user, friends.id_friend FROM users JOIN friends ON users.id_user = friends.id_user)\n" +
                "      AS join_u_f WHERE join_u_f.id_friend = :idUser)\n" +
                "        AS home WHERE (posts.id_user = home.id_user OR posts.id_user = home.id_friend))\n" +
                "  UNION\n" +
                "SELECT posts.* FROM posts JOIN\n" +
                "  (SELECT id_user FROM users WHERE visibility = :visibility) AS visible ON posts.id_user =  visible.id_user) AS result")
                .setParameter("idUser", idUser).setParameter("visibility", PUBLIC.ordinal()).uniqueResult()).intValue();
    }
}
