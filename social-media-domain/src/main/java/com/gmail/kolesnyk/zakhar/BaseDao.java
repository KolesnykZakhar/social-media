package com.gmail.kolesnyk.zakhar;


import java.util.List;

/**
 * interface contains main methods that required for ORM relations
 */
public interface BaseDao<T, I> {

    /**
     * return entity by primary key in table
     */
    T selectById(I id);

    /**
     * add new row with describe of entity in suited table
     */
    void save(T object);

    /**
     * update row where described entity in suited table
     */
    void update(T object);

    /**
     * remove row where described entity from suited table
     */
    void remove(T object);

    /**
     * return list of all entities from suited table
     */
    List<T> list();
}
