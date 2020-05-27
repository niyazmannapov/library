package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PrePostInvocationAttributeFactory;
import org.springframework.stereotype.Component;
import ru.itis.model.Author;
import ru.itis.model.Book;
import ru.itis.repository.AuthorRepository;

import java.util.List;

@Component
public interface AuthorService {

    Author find(Long id);

    void save(Author author);

    Author findByName(String firstName, String lastName);


}
