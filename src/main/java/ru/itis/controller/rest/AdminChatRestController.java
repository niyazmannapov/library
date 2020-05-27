package ru.itis.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.Message;
import ru.itis.service.impl.AdminChatService;
import ru.itis.dto.Messages;

import java.util.List;

@RestController
public class AdminChatRestController {

    private Messages m = Messages.getInstance();

    @Autowired
    private AdminChatService repository;


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseBody
    @RequestMapping(value = "/api/admin/get")
    public List<Message> getMessage() {

        System.out.println("/admin/get , thread " + Thread.currentThread().getId());

        List<Message> msgs = null;

        synchronized (m) {
            try {
                m.wait();
                msgs = repository.getMessage();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return msgs;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseBody
    @RequestMapping(value = "/api/admin/send/{userId}")
    public Message getMessage(@RequestBody Message body, @PathVariable Integer userId) {

        System.out.println("/admin/send " + body.getUserid() + "," + body.getMsg() +
                ", thread " + Thread.currentThread().getId() + userId);

        repository.sendMessage(body, userId);
        return body;
    }
}
