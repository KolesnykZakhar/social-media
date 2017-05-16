package com.gmail.kolesnyk.zakhar.chatService;

import com.gmail.kolesnyk.zakhar.chatService.chat.Chat;
import com.gmail.kolesnyk.zakhar.chatService.chat.ChatsMenu;

/**
 * The {@code ChatService} service class for operations associated with sending messages between Users
 *
 * @author Kolesnyk Zakhar
 * @see com.gmail.kolesnyk.zakhar.chatService.chat.Chat
 * @see com.gmail.kolesnyk.zakhar.chatService.chat.ChatHeader
 * @see com.gmail.kolesnyk.zakhar.chatService.chat.ChatsMenu
 * @since JDK1.8
 */
public interface ChatService {

    /**
     * method allowed to get full chat by two Users
     *
     * @param idUser         ID User
     * @param idInterlocutor ID Interlocutor
     * @return {@link Chat} example of chat
     */
    Chat getFullChatByUsers(int idUser, int idInterlocutor);

    /**
     * method allowed to get short chat by two Users
     *
     * @param idUser         ID User
     * @param idInterlocutor ID Interlocutor
     * @return {@link Chat} example of chat
     */
    Chat getShortChatByUsers(int idUser, int idInterlocutor);

    /**
     * method allowed to save message from User to it Interlocutor
     *
     * @param idUser         ID User
     * @param idInterlocutor ID Interlocutor
     */
    void saveMessage(String textMessage, int idUser, int idInterlocutor);

    /**
     * method allowed to get chats menu {@link ChatsMenu} of User, by it ID
     *
     * @param idUser ID User
     * @return {@link ChatsMenu} example of chats menu
     */
    ChatsMenu getChatsMenu(int idUser);

    /**
     * method allowed to mark all messages as read from Interlocutor to User by their IDs
     *
     * @param idUser         ID User
     * @param idInterlocutor ID Interlocutor
     */
    void markMessagesAsReadByUsers(int idUser, int idInterlocutor);

    /**
     * method allowed to get amount of all unread messages addressed to User
     *
     * @param idUser ID User
     * @return {@link Integer} amount unread messages
     */
    Integer amountUnreadMessages(int idUser);
}
