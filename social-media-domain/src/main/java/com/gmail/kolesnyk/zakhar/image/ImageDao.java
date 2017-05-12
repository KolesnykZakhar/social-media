package com.gmail.kolesnyk.zakhar.image;

import com.gmail.kolesnyk.zakhar.BaseDao;

import java.util.List;

public interface ImageDao extends BaseDao<Image, Integer> {

    List<Image> listByIdUser(int idUser);
}
