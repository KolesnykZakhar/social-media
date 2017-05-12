package com.gmail.kolesnyk.zakhar.post;

import com.gmail.kolesnyk.zakhar.AbstractDao;
import com.gmail.kolesnyk.zakhar.user.User;
import javafx.geometry.Pos;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

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
//        return sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM posts")
//                .addEntity(Post.class).setParameter("idUser",idUser).setParameter("offset",offset).setParameter("amount",amount).list();
        return null;
    }

    @Override
    public Integer amountBookmarksByIdUser(int idUser) {
        return null;
    }

    @Override
    public List<Post> sublistNewsByUser(int idUser, int offset, int amount) {
        return null;
    }

    @Override
    public Integer amountNewsByIdUser(int idUser) {
        return null;
    }
}
