package ru.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.model.Author;
import ru.itis.model.Book;
import ru.itis.repository.AuthorRepository;
import ru.itis.service.AuthorService;

import java.util.List;
@Component
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;


    @Override
    public Author find(Long id) {
        return authorRepository.find(id).get();
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
        return;
    }

    @Override
    public Author findByName(String firstName, String lastName) {
        List<Author> authors = authorRepository.findByName(firstName, lastName);
        if (authors.isEmpty()) return null;
        return authors.get(0);
    }


}
