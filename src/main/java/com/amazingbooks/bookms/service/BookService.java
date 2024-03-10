package com.amazingbooks.bookms.service;

import com.amazingbooks.bookms.dao.BookRepo;
import com.amazingbooks.bookms.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public String save(List<Book> book) {
        for (Book books : book) {
            String author = books.getAuthor();
            if (bookRepo.existsByAuthor(author)) {
                throw new IllegalArgumentException("An author with the same name already exists.");
            }
            bookRepo.saveAll(book);
        }
        return "Saved";
    }

    public List<Book> findAll() {
        List<Book> book = bookRepo.findAll();
        return book;
    }

    public List<Book> findByIsbn(String isbn) {
        List<Book> book = (List<Book>) bookRepo.findByIsbn(isbn);
        return book;
    }

    public void deleteBookByIsbn(String isbn) {
        bookRepo.deleteByIsbn(isbn);
    }

    public Book updateBook(String isbn, Book updatedBook) {
        Book existingBook = bookRepo.findByIsbn(isbn);
        if (existingBook != null) {
            String author = updatedBook.getAuthor();
            if (bookRepo.existsByAuthor(author)) {
                throw new IllegalArgumentException("An author with the same name already exists.");
            }
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setPublishedDate(updatedBook.getPublishedDate());
            existingBook.setTotalCopies(updatedBook.getTotalCopies());
            existingBook.setIssuedCopies(updatedBook.getIssuedCopies());
            existingBook.setAuthor(updatedBook.getAuthor());
            return bookRepo.save(existingBook);
        } else {
            return null;
        }
    }
}
