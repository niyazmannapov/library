package ru.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.model.Chat;
import ru.itis.repository.ChatRepository;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.service.ChatService;

import java.util.Collections;
import java.util.HashMap;

@Controller
public class ChatController {

    @Autowired
    private ChatRepository repository;
    @Autowired
    private ChatService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/chats")
    public String chats(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model){
        if (userDetails == null) return ("redirect:/signIn");
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", userDetails.getUser());
        if (userDetails.getRole().equals("ADMIN")) {
            model.addAttribute("chats", repository.findAll());
        } else {
            model.addAttribute("chats", Collections.singletonList(repository
                    .findChatByUserId(userDetails.getUserId()).get()));
        }
        return "chats";
    }

    @PreAuthorize("permitAll()")
    @PostMapping(value = "/chats")
    public ModelAndView createChat(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam String chatName) {
        if (userDetails == null) return new ModelAndView("redirect:/signIn");
        service.createChat(chatName, userDetails.getUser());
        return new ModelAndView("redirect:/chats");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/chat/{userId}")
    public ModelAndView getChat(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                @PathVariable Integer userId, @ModelAttribute("model") ModelMap model) {
        if (userDetails == null) return new ModelAndView("redirect:/signIn");
        ModelAndView maw = new ModelAndView();
        if (userDetails.getRole().equals("ADMIN")) {
            maw.setViewName("admin");
            maw.addObject("user", userId);
        } else {
            maw.setViewName("user");
        }
        maw.addObject("history", service.history(userId));
        maw.addObject("userId", userDetails.getUserId());
        return maw;
    }

}
