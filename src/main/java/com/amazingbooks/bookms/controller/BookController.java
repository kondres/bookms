package com.amazingbooks.bookms.controller;

import com.amazingbooks.bookms.model.Book;
import com.amazingbooks.bookms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/saveBook")
    public ResponseEntity<?> saveBook(@RequestBody List<Book> book) {
        try {
            bookService.save(book);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CREATED);
        }
    }

    @GetMapping("/fetchAllBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/fetchBook/{isbn}")
    public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable String isbn) {
        List<Book> books = bookService.findByIsbn(isbn);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @DeleteMapping("/deleteBookByIsbn/{isbn}")
    public ResponseEntity<Void> deleteBookByIsbn(@PathVariable String isbn) {
        bookService.deleteBookByIsbn(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/updateBookByIsbn/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable String isbn, @RequestBody Book updateBook) {
        Book updated = bookService.updateBook(isbn, updateBook);
        if (updated != null)
            return new ResponseEntity<>(updated, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
