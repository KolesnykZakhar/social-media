package com.gmail.kolesnyk.zakhar.post;

import com.gmail.kolesnyk.zakhar.AbstractDao;
import com.gmail.kolesnyk.zakhar.user.User;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * class implements and extends methods that need to ORM relations with {@link Post} class
 */
public class PostDaoImpl extends AbstractDao<Post, Integer> implements PostDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> listByIdUser(Integer idUser) {
        return session.createSQLQuery("SELECT * FROM posts WHERE id_user = :idUser ORDER BY date_post DESC")
                .addEntity(Post.class).setParameter("idUser", idUser).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> listByUser(User user) {
        return session.createCriteria(Post.class).add(Restrictions.eq("user", user)).addOrder(Order.desc("datePost")).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> listSearchString(String search) {
        return session.createSQLQuery("SELECT * FROM posts WHERE comment LIKE :search")
                .addEntity(Post.class).setParameter("search", "%" + search + "%").list();
    }
}
