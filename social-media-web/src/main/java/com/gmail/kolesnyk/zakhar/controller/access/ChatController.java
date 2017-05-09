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

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    private static User currentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @RequestMapping(value = "/user/full_chat/{idUser}")
    public ModelAndView openFullChat(@PathVariable("idUser") Integer idUser) {
        ModelAndView modelAndView = new ModelAndView("full_chat");
        Chat chat = chatService.getFullChatByUsers(currentUser().getIdUser(), idUser);
        modelAndView.addObject("chat", chat);
        return modelAndView;
    }

    @RequestMapping(value = "/user/full_chat/send_message")
    public ModelAndView sendMessageFullChat(@RequestParam("textMessage") String textMessage, @RequestParam("idUser") Integer idUser, @RequestParam("idInterlocutor") Integer idInterlocutor) {
        chatService.saveMessage(textMessage, idUser, idInterlocutor);
        ModelAndView modelAndView = new ModelAndView("full_chat");
        Chat chat = chatService.getFullChatByUsers(idUser, idInterlocutor);
        modelAndView.addObject("chat", chat);
        return modelAndView;
    }

    @RequestMapping(value = "/user/short_chat/{idUser}/{hasUnread}")
    public ModelAndView openShortChat(@PathVariable("idUser") Integer idUser, @PathVariable("hasUnread") Boolean hasUnread) {
        if (hasUnread) {
            chatService.markMessagesAsReadByUsers(currentUser().getIdUser(), idUser);
        }
        ModelAndView modelAndView = new ModelAndView("short_chat");
        Chat chat = chatService.getShortChatByUsers(currentUser().getIdUser(), idUser);
        modelAndView.addObject("chat", chat);
        return modelAndView;
    }

    @RequestMapping(value = "/user/short_chat/send_message")
    public ModelAndView sendMessageShortChat(@RequestParam("textMessage") String textMessage, @RequestParam("idUser") Integer idUser, @RequestParam("idInterlocutor") Integer idInterlocutor) {
        chatService.saveMessage(textMessage, idUser, idInterlocutor);
        ModelAndView modelAndView = new ModelAndView("short_chat");
        Chat chat = chatService.getShortChatByUsers(idUser, idInterlocutor);
        modelAndView.addObject("chat", chat);
        return modelAndView;
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
}
