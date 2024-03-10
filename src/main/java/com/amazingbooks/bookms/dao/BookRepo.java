package com.amazingbooks.bookms.dao;

import com.amazingbooks.bookms.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, String> {
    List<Book> findByAuthor(String author);

    List<Book> deleteByIsbn(String isbn);

    Book findByIsbn(String isbn);

    boolean existsByAuthor(String author);
}
