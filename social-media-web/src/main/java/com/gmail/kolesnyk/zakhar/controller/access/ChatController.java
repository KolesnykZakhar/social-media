package com.gmail.kolesnyk.zakhar.controller.access;


import com.gmail.kolesnyk.zakhar.chatService.ChatService;
import com.gmail.kolesnyk.zakhar.chatService.chat.Chat;
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

    @RequestMapping(value = "/user/chat/{idUser}")
    public ModelAndView openChat(@PathVariable("idUser") Integer idUser) {
        ModelAndView modelAndView = new ModelAndView("chat");
        Integer idCurrentUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdUser();
        Chat chat = chatService.getChatByUsers(idCurrentUser, idUser);
        modelAndView.addObject("chat", chat);
        return modelAndView;
    }

    @RequestMapping(value = "/user/send_message")
    public ModelAndView sendMessage(@RequestParam("textMessage") String textMessage, @RequestParam("idUser") Integer idUser, @RequestParam("idInterlocutor") Integer idInterlocutor) {
        System.out.println("\n\n\n\n" + textMessage + " " + idUser + " " + idInterlocutor);
        chatService.saveMessage(textMessage, idUser, idInterlocutor);
        ModelAndView modelAndView = new ModelAndView("chat");
//        Integer idCurrentUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdUser();
        Chat chat = chatService.getChatByUsers(idUser, idInterlocutor);
        modelAndView.addObject("chat", chat);
        return modelAndView;
//        return null;
    }
}
