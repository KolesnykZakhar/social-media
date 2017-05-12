package com.gmail.kolesnyk.zakhar.image;

import com.gmail.kolesnyk.zakhar.AbstractDao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImageDaoImpl extends AbstractDao<Image, Integer> implements ImageDao {
    @Override
    @SuppressWarnings("unchecked")
    public List<Image> listByIdUser(int idUser) {
        return sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM images WHERE id_user = :idUser")
                .addEntity(Image.class).setParameter("idUser", idUser).list();
    }
}
