package com.payu.southafrica.managementservice.services;

import com.payu.southafrica.managementservice.dto.BookDto;
import com.payu.southafrica.managementservice.persistence.BookEntity;
import com.payu.southafrica.managementservice.persistence.BookRepository;
import com.payu.southafrica.managementservice.services.exception.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceTest {
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookService = new BookService(bookRepository);
    }

    @Test
    void getAllBooks_ReturnsListOfBooks() {
        List<BookEntity> books = Collections.singletonList(new BookEntity());
        when(bookRepository.findAll()).thenReturn(books);

        List<BookDto> result = bookService.getAllBooks();

        assertThat(bookRepository.findAll()).isNotNull();
        assertThat(result).hasSize(1);
    }

    @Test
    void getBookById_ExistingBookId_ReturnsBookDto() {
        when(bookRepository.findById(createBookDto().getId())).thenReturn(Optional.of(createBookEntity()));

        BookDto result = bookService.getBookById(createBookDto().getId());

        assertThat(result).isNotNull();
        assertThat(bookRepository.findById(createBookDto().getId())).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void getBookById_NonExistingBookId_ThrowsBookNotFoundException() {
        when(bookRepository.findById(any())).thenReturn(Optional.empty());

        assertThatExceptionOfType(BookNotFoundException.class).isThrownBy(() -> bookService.getBookById(createBookDto().getId()));
    }

    @Test
    void saveBook_ValidBookDto_BookRepositorySaveCalled() {
        bookService.saveBook(createBookDto());

        verify(bookRepository, times(1)).save(any(BookEntity.class));
    }

    @Test
    void updateBook_ExistingBookId_ValidBookDto_BookRepositorySaveCalled() {
        when(bookRepository.findById(any())).thenReturn(Optional.of(createBookEntity()));

        bookService.updateBook(createBookDto().getId(), createBookDto());

        verify(bookRepository, times(1)).save(any(BookEntity.class));
    }

    @Test
    void updateBook_NonExistingBookId_ThrowsBookNotFoundException() {
        when(bookRepository.findById(createBookDto().getId())).thenReturn(Optional.empty());

        assertThatExceptionOfType(BookNotFoundException.class).isThrownBy(() -> bookService.updateBook(any(), createBookDto()));
    }

    @Test
    void deleteBook_ExistingBookId_BookRepositoryDeleteByIdCalled() {
        bookService.deleteBook(createBookDto().getId());

        verify(bookRepository, times(1)).deleteById(createBookDto().getId());
    }

    private BookDto createBookDto() {
        return BookDto.builder()
                .id(1L)
                .name("Sample Book")
                .isbn("ISBN-001")
                .publishDate(LocalDate.parse("2023-06-12"))
                .price(19.99)
                .bookType("Soft Copy")
                .build();
    }

    private BookEntity createBookEntity() {
        return BookEntity.builder()
                .id(1L)
                .name("Sample Book")
                .isbn("ISBN-001")
                .publishDate(LocalDate.parse("2023-06-12"))
                .price(19.99)
                .bookType("Hard Copy")
                .build();
    }
}
