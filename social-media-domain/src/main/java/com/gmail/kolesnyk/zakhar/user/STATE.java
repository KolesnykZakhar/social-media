package com.gmail.kolesnyk.zakhar.user;

/**
 * The {@code STATE} enum class that contains types of user states
 *
 * @author Kolesnyk Zakhar
 * @see com.gmail.kolesnyk.zakhar.user.User
 * @since JDK1.8
 */
public enum STATE {

    /**
     * State type: available
     */
    AVAILABLE,

    /**
     * State type: banned
     */
    BANNED,

    /**
     * State type: waiting of confirm email
     */
    WAITING_CONFIRM
}
