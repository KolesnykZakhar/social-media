package com.gmail.kolesnyk.zakhar.userService.userActivityMap;

/**
 * The {@code UserActivityMap} class used for storing info about Users Activity in system
 *
 * @author Kolesnyk Zakhar
 * @since JDK1.8
 */
public interface UserActivityMap {

    /**
     * method allowed to put last ID User that make any actions
     *
     * @param idUser ID User
     */
    void put(Integer idUser);

    /**
     * method allowed to know that user did make any actions less than 15 minutes ago
     *
     * @param idUser ID User
     * @return true if user did make any actions less than 15 min ago,
     * false if User did not make any actions more than 15 min ago
     */
    boolean isOnline(Integer idUser);
}
