package com.payu.southafrica.uiservicethymeleaf.controller;

import com.payu.southafrica.uiservicethymeleaf.components.BookApiClient;
import com.payu.southafrica.uiservicethymeleaf.model.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookApiClient bookApiClient;

    @GetMapping("/all-books")
    public String getAllBooks(Model model) {
        List<BookDto> books = bookApiClient.getAllBooks();
        model.addAttribute("books", books);
        return "all-books";
    }

    @GetMapping("/add-book")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new BookDto());
        return "add-book";
    }

    @PostMapping("/add-book")
    public String addBook(@ModelAttribute BookDto book) {
        bookApiClient.addBook(book);
        return "redirect:/books/all-books";
    }

    @GetMapping("/edit-book/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        BookDto book = bookApiClient.getBookById(id);
        model.addAttribute("book", book);
        return "edit-book";
    }

    @PostMapping("/edit-book/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute BookDto book) {
        bookApiClient.updateBook(id, book);
        return "redirect:/books/all-books";
    }

    @GetMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookApiClient.deleteBook(id);
        return "redirect:/books/all-books";
    }
}
