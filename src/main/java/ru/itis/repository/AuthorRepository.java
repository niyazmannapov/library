package ru.itis.repository;

import ru.itis.model.Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Long, Author> {
    List findByName(String firstName, String lastName);
}
