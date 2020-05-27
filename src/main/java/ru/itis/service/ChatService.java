package ru.itis.service;


import ru.itis.model.Message;
import ru.itis.model.User;

import java.util.List;

public interface ChatService {
    List<Message> history(Integer id);
    void createChat(String chatName, User user);
}
