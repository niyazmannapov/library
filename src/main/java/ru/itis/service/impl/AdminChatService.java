package ru.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.dto.Message;
import ru.itis.dto.Messages;
import ru.itis.model.User;
import ru.itis.repository.MessageRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminChatService {
    @Autowired
    MessageRepository messageRepository;

    private Messages m = Messages.getInstance();

    public List<Message> getAllMessages() {

        List<Message> msgs = new ArrayList<>();

        m.messages.forEach((userid, userMessages) -> {
            for (String s : userMessages) {
                msgs.add(new Message( userid, s));
            }
        });

        return msgs;
    }

    public synchronized List<Message> getMessage() {

        List<Message> msgs = new ArrayList<>();

        m.newmessage.forEach((userid, userMessage) -> {
                msgs.add(new Message( userid, userMessage));
        });

        m.newmessage.clear();

        return msgs;
    }

    public void sendMessage(Message msg, Integer userId) {
        ru.itis.model.Message message1 = ru.itis.model.Message.builder()
                .sender(User.builder().id(msg.getUserid()).build())
                .time(new Date(new java.util.Date().getTime()))
                .message(msg.getMsg())
//                .chat_id(Chat.builder().id(100L).build())
                .build();
        messageRepository.save(message1);
        synchronized (m) {
            System.out.println("in repository, in synchronized block put message");
            m.fromadminmessage.put(userId, msg);
            m.notifyAll();
        }
    }
}
