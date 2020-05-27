package ru.itis.service;


import ru.itis.model.Book;

import java.util.List;

public interface BookService {
    void save(Book book);
    List<Book> getAll();
    Book find(Long id);
}
