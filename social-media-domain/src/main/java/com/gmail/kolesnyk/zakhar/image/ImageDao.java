package com.gmail.kolesnyk.zakhar.image;

import com.gmail.kolesnyk.zakhar.BaseDao;

import java.util.List;

/**
 * The {@code ImageDao} class DAO for entity {@link Image}
 *
 * @author Kolesnyk Zakhar
 * @see com.gmail.kolesnyk.zakhar.AbstractDao
 * @see com.gmail.kolesnyk.zakhar.image.ImageDaoImpl
 * @since JDK1.8
 */
public interface ImageDao extends BaseDao<Image, Integer> {

    /**
     * method allow to get {@link List<Image>} by ID of User
     *
     * @param idUser ID of User
     * @return {@link List<Image>} list images
     */
    List<Image> listByIdUser(int idUser);
}
