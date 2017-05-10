package com.gmail.kolesnyk.zakhar.controller.access;


import com.gmail.kolesnyk.zakhar.chatService.ChatService;
import com.gmail.kolesnyk.zakhar.chatService.chat.Chat;
import com.gmail.kolesnyk.zakhar.chatService.chat.ChatsMenu;
import com.gmail.kolesnyk.zakhar.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static com.gmail.kolesnyk.zakhar.controller.access.ChatController.TYPE_CHAT.FULL;
import static com.gmail.kolesnyk.zakhar.controller.access.ChatController.TYPE_CHAT.SHORT;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @RequestMapping(value = "/user/full_chat/{idUser}")
    public ModelAndView openFullChat(@PathVariable("idUser") Integer idUser) {
        return getChatModel(FULL, idUser);
    }

    @RequestMapping(value = "/user/full_chat/send_message")
    public ModelAndView sendMessageFullChat(@RequestParam("textMessage") String textMessage, @RequestParam("idInterlocutor") Integer idInterlocutor) {
        chatService.saveMessage(textMessage, currentUser().getIdUser(), idInterlocutor);
        return getChatModel(FULL, idInterlocutor);
    }

    @RequestMapping(value = "/user/short_chat/{idUser}/{hasUnread}")
    public ModelAndView openShortChat(@PathVariable("idUser") Integer idUser, @PathVariable("hasUnread") Boolean hasUnread) {
        if (hasUnread) {
            chatService.markMessagesAsReadByUsers(currentUser().getIdUser(), idUser);
        }
        return getChatModel(SHORT, idUser);
    }

    @RequestMapping(value = "/user/short_chat/send_message")
    public ModelAndView sendMessageShortChat(@RequestParam("textMessage") String textMessage, @RequestParam("idInterlocutor") Integer idInterlocutor) {
        chatService.saveMessage(textMessage, currentUser().getIdUser(), idInterlocutor);
        return getChatModel(SHORT, idInterlocutor);
    }

    @RequestMapping(value = "/user/chats_menu")
    public ModelAndView sendMessage() {
        ChatsMenu chatsMenu = chatService.getChatsMenu(currentUser().getIdUser());
        ModelAndView modelAndView = new ModelAndView("chats_menu");
        modelAndView.addObject("chatsMenu", chatsMenu);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/user/update_message_mark")
    public String updateMessageMark() {
        Integer amount = chatService.amountUnreadMessages(currentUser().getIdUser());
        if (amount != null && amount > 0) {
            return amount.toString();
        } else {
            return "";
        }
    }

    private static User currentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private ModelAndView getChatModel(TYPE_CHAT typeChat, int idInterlocutor) {
        ModelAndView modelAndView;
        Chat chat;
        switch (typeChat) {
            case FULL: {
                modelAndView = new ModelAndView("full_chat");
                chat = chatService.getFullChatByUsers(currentUser().getIdUser(), idInterlocutor);
                break;
            }
            case SHORT: {
                modelAndView = new ModelAndView("short_chat");
                chat = chatService.getShortChatByUsers(currentUser().getIdUser(), idInterlocutor);
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown type chat");
            }
        }
        modelAndView.addObject("chat", chat);
        return modelAndView;
    }

    enum TYPE_CHAT {
        SHORT, FULL
    }
}
