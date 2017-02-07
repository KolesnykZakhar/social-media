package com.gmail.kolesnyk.zakhar;

import com.gmail.kolesnyk.zakhar.user.User;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

//        session.getTransaction().begin();
//        try {
//            session.createCriteria(User.class).list().forEach(a -> ((User) a).getPostList().forEach(p -> System.out.println(p.getComment())));
        session.createCriteria(User.class).list().forEach(a -> ((User) a).getFriends().keySet()
                .forEach(p -> System.out.println(((User) a).getFirstName() + " get friend: " + p.getFirstName() + " in " + ((User) a).getFriends().get(p))));
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//            throw e;
//        } finally {
//            session.close();
//        }
    }
}
