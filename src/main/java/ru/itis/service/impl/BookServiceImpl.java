package ru.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.itis.model.Book;
import ru.itis.repository.BookRepository;
import ru.itis.service.BookService;

import java.util.List;

@Component
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;


    public void save(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book find(Long id) {
        return bookRepository.find(id).get();
    }
}
