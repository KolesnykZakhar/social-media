package com.gmail.kolesnyk.zakhar;

import com.gmail.kolesnyk.zakhar.configurations.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * class implements main methods that required for ORM relations
 */
@Component
public abstract class AbstractDao<T, I extends Serializable> implements BaseDao<T, I> {

    @Autowired
    protected SessionFactory sessionFactory;

    /**
     * class type of entity
     */
    private final Class<T> entityClass;

//    /**
//     * hibernate session for ORM operations
//     */
//    protected static Session session;


    @SuppressWarnings("unchecked")
    public AbstractDao() {
//        try {
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//        } catch (HibernateException ex) {
//            session = HibernateUtil.getSessionFactory().openSession();
//        }
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

//    /**
//     * method hiding transactions
//     */
//    private static void doTransaction(Transaction transaction) {
//        try {
//            session.getTransaction().begin();
//            transaction.transact();
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//            throw e;
//        } finally {
//            session.close();
//        }
//    }

//    /**
//     * interface used to hide transactions
//     */
//    interface Transaction {
//        void transact();
//    }


    @Override
    @SuppressWarnings("unchecked")
    public T selectById(I id) {
//        return (T) session.load(entityClass, id);
        return (T) sessionFactory.getCurrentSession().load(entityClass, id);
    }

    @Override
    public void save(T object) {
//        doTransaction(() -> session.save(object));
        sessionFactory.getCurrentSession().save(object);
    }

    @Override
    public void update(T object) {
//        doTransaction(() -> session.update(object));
        sessionFactory.getCurrentSession().update(object);
    }

    @Override
    public void remove(T object) {
//        doTransaction(() -> session.delete(object));
        sessionFactory.getCurrentSession().delete(object);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> list() {
//        return session.createCriteria(entityClass).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return sessionFactory.getCurrentSession().createCriteria(entityClass).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
}
