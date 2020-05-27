package ru.itis.repository;

import ru.itis.model.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Long, Message> {
    List<Message> findByUser(Integer id);

}
