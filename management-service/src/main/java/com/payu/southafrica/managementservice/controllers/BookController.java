package com.payu.southafrica.managementservice.controllers;

import com.payu.southafrica.managementservice.dto.BookDto;
import com.payu.southafrica.managementservice.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @Value("${default.page}")
    private int defaultPage;

    @Value("${default.size}")
    private int defaultSize;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(
            @RequestParam(defaultValue = "${default.page}") int page,
            @RequestParam(defaultValue = "${default.size}") int size
        ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BookDto> bookPage = bookService.getAllBooks(pageable);

        return new ResponseEntity<>(bookPage.getContent(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable final Long id) {
        log.info("Retrieving book with id: {}", id);
        return bookService.getBookById(id);
    }

    @PostMapping
    public void addBook(@RequestBody BookDto book) {
        log.info("Adding a new book: {}", book);
        bookService.saveBook(book);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable final Long id, @RequestBody BookDto book) {
        log.info("Updating book: {} with id: {}", book, id);
        bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable final Long id) {
        log.info("Deleted book with id: {}", id);
        bookService.deleteBook(id);
    }

}
