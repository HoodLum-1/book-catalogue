package com.payu.southafrica.managementservice.controllers;

import com.payu.southafrica.managementservice.dto.BookDto;
import com.payu.southafrica.managementservice.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @GetMapping(path = "/all-books")
    public List<BookDto> getAllBooks() {
        log.info("Retrieving all books");

        return bookService.getAllBooks();
    }

    @GetMapping(path = "/book/{id}")
    public BookDto getBookById(@PathVariable final Long id) {
        log.info("Retrieving book with id: {}", id);
        return bookService.getBookById(id);
    }

    @PostMapping(path = "/add-book")
    public void addBook(@RequestBody BookDto book) {
        log.info("Adding a new book: {}", book);
        bookService.saveBook(book);
    }

    @PutMapping("/update-book/{id}")
    public void updateBook(@PathVariable final Long id, @RequestBody BookDto book) {
        log.info("Updating book: {} with id: {}", book, id);
        bookService.updateBook(id, book);
    }

    @DeleteMapping(path = "/delete-book/{id}")
    public void deleteBook(@PathVariable final Long id) {
        log.info("Deleted book with id: {}", id);
        bookService.deleteBook(id);
    }

}
