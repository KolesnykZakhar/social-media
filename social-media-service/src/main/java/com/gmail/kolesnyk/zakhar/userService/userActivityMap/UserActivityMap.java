package com.gmail.kolesnyk.zakhar.userService.userActivityMap;


public interface UserActivityMap {

    void put(Integer idUser);

    boolean isOnline(Integer idUser);
}
