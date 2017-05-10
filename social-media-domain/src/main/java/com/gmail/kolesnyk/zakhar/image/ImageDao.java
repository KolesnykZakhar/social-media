package com.gmail.kolesnyk.zakhar.image;

import com.gmail.kolesnyk.zakhar.BaseDao;
import com.gmail.kolesnyk.zakhar.message.Message;
import com.gmail.kolesnyk.zakhar.user.User;

import java.util.List;
import java.util.Set;

public interface ImageDao extends BaseDao<Image, Integer> {

    List<Image> listByIdUser(int idUser);
}
