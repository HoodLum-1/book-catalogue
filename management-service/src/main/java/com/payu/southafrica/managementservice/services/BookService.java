package com.payu.southafrica.managementservice.services;

import com.payu.southafrica.managementservice.dto.BookDto;
import com.payu.southafrica.managementservice.persistence.BookEntity;
import com.payu.southafrica.managementservice.persistence.BookRepository;
import com.payu.southafrica.managementservice.services.exception.BookNotFoundException;
import com.payu.southafrica.managementservice.utils.BookMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.payu.southafrica.managementservice.utils.BookMapper.mapToDto;
import static com.payu.southafrica.managementservice.utils.BookMapper.mapToEntity;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<BookDto> getAllBooks() {
        try {
            return bookRepository.findAll().stream()
                    .map(BookMapper::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("An error occurred while retrieving books: {}", e.getMessage());
            throw new BookNotFoundException(e.getMessage());
        }
    }

    public BookDto getBookById(Long id) {
        return mapToDto(bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException(String.format("Book with id %d not found", id))));
    }

    public void saveBook(BookDto book) {
        bookRepository.save(mapToEntity(book));
    }

    public void updateBook(Long bookId, BookDto bookDto) {
        BookEntity existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book with id %d not found", bookId)));

        BookEntity updatedBook = BookMapper.mapToEntity(bookDto);
        updatedBook.setId(existingBook.getId());

        bookRepository.save(updatedBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
