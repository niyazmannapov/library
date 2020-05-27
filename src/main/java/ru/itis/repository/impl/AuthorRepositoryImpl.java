package ru.itis.repository.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.model.Author;
import ru.itis.model.Book;
import ru.itis.repository.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorRepositoryImpl implements AuthorRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Optional<Author> find(Long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public List<Author> findAll() {
        return entityManager.createQuery("SELECT s FROM Author s").getResultList();
    }

    @Transactional
    public void save(Author entity) {
        entityManager.persist(entity);
    }


    public List<Author> findByName(String firstName, String lastName) {
        Query query = entityManager.createQuery("SELECT s FROM Author s where s.firstName=:first and s.lastName=:last");
        query.setParameter("first", firstName);
        query.setParameter("last", lastName);
        return query.getResultList();
    }

    @Override
    public void delete(Long aLong) {
        return;
    }


    public void update(Author entity) {
        return;
    }
}
