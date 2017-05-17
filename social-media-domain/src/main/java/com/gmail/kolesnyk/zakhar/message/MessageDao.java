package com.gmail.kolesnyk.zakhar.message;

import com.gmail.kolesnyk.zakhar.BaseDao;
import com.gmail.kolesnyk.zakhar.user.User;

import java.util.List;

/**
 * The {@code MessageDao} class DAO for entity {@link Message}
 *
 * @author Kolesnyk Zakhar
 * @see com.gmail.kolesnyk.zakhar.AbstractDao
 * @see com.gmail.kolesnyk.zakhar.message.MessageDao
 * @since JDK1.8
 */
public interface MessageDao extends BaseDao<Message, Integer> {

    /**
     * method allow to get all messages between two users {@link List<Message>}
     * by ID of User and ID of his friend
     *
     * @param idUser   ID of User
     * @param idFriend ID of Friend
     * @return {@link List<Message>} all messages
     */
    List<Message> getFullChat(Integer idUser, Integer idFriend);

    /**
     * method allow to get amount messages between User and his Friend
     * by ID of User and ID of his friend
     *
     * @param idUser   ID of User
     * @param idFriend ID of Friend
     * @return {@link Integer} amount messages
     */
    Integer amountMessagesByUsers(Integer idUser, Integer idFriend);

    /**
     * method allow to get last messages between two users {@link List<Message>}
     * by ID of User, ID of his friend and amount of messages
     *
     * @param idUser   ID of User
     * @param idFriend ID of Friend
     * @param size     amount messages that need to return
     * @return {@link List<Message>} sub-list messages
     */
    List<Message> getShortChat(int idUser, int idFriend, int size);

    /**
     * method allow to get list of all Users with what current User has exchanged of messages
     * {@link List<User>} by ID of User
     *
     * @param idUser ID of User
     * @return {@link List<User>} list of users
     */
    List<User> getInterlocutors(int idUser);

    /**
     * method allow to get last message between two users {@link Message}
     * by ID of User and ID his Interlocutor
     *
     * @param idUser         ID of User
     * @param idInterlocutor ID of Interlocutor
     * @return {@link Message} last Message
     */
    Message getLastMessage(int idUser, int idInterlocutor);

    /**
     * method allow to get amount unread messages by User {@link Integer} from concrete Interlocutor
     * by ID of User and ID his Interlocutor
     *
     * @param idUser         ID of User
     * @param idInterlocutor ID of Interlocutor
     * @return {@link Integer} amount unread messages
     */
    Integer amountUnreadMessagesByUsers(int idUser, int idInterlocutor);

    /**
     * method allow to get amount unread messages by User {@link Integer}
     * from all Interlocutors, by ID of User
     *
     * @param idUser ID of User
     * @return {@link Integer} amount unread messages
     */
    Integer amountUnreadMessages(int idUser);

    /**
     * method allow to change status of all unread messages {@link Message#unread}
     * from from 'unread' to 'read', by ID User and ID Interlocutor
     *
     * @param idUser         ID of User
     * @param idInterlocutor ID of Interlocutor
     */
    void markMessagesAsReadByUsers(int idUser, int idInterlocutor);
}
