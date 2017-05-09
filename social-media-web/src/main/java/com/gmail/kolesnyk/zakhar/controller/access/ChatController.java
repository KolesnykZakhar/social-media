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
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @RequestMapping(value = "/user/full_chat/{idUser}")
    public ModelAndView openFullChat(@PathVariable("idUser") Integer idUser) {
        ModelAndView modelAndView = new ModelAndView("full_chat");
        Integer idCurrentUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdUser();
        Chat chat = chatService.getFullChatByUsers(idCurrentUser, idUser);
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

    @RequestMapping(value = "/user/short_chat/{idUser}")
    public ModelAndView openShortChat(@PathVariable("idUser") Integer idUser) {
        ModelAndView modelAndView = new ModelAndView("short_chat");
        Integer idCurrentUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdUser();
        Chat chat = chatService.getShortChatByUsers(idCurrentUser, idUser);
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
        Integer idUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdUser();
        ChatsMenu chatsMenu = chatService.getChatsMenu(idUser);
        ModelAndView modelAndView = new ModelAndView("chats_menu");
        modelAndView.addObject("chatsMenu", chatsMenu);
        return modelAndView;
    }
}
