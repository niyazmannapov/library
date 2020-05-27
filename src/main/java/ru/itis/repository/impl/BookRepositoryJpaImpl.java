package ru.itis.repository.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.model.Book;
import ru.itis.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component
public class BookRepositoryJpaImpl implements BookRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Optional<Book> find(Long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public List findAll() {
        return entityManager.createQuery("SELECT s FROM Book s").getResultList();
    }

    @Transactional
    @Override
    public void save(Book entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Long aLong)
    {
        return;
    }

    @Override
    public void update(Book entity) {

    }


}
