package com.payu.southafrica.managementservice.utils;

import com.payu.southafrica.managementservice.persistence.BookEntity;
import com.payu.southafrica.managementservice.persistence.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {
    private final BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeSampleData();
    }

    private void initializeSampleData() {
        String[][] bookData = {
                {"Book 1", "ISBN-001", "10/01/2023", "29.99", "Hard Cover"},
                {"Book 2", "ISBN-002", "20/02/2023", "19.99", "Soft Cover"},
                {"Book 3", "ISBN-003", "30/03/2023", "9.99", "eBook"},
                {"Book 4", "ISBN-004", "11/04/2023", "29.99", "Hard Cover"},
                {"Book 5", "ISBN-005", "22/05/2023", "19.99", "Soft Cover"},
                {"Book 6", "ISBN-006", "15/06/2023", "9.99", "eBook"}
        };

        for (String[] data : bookData) {
            BookEntity bookEntity = BookEntity.builder()
                    .name(data[0])
                    .isbn(data[1])
                    .publishDate(LocalDate.parse(data[2], DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .price(Double.parseDouble(data[3]))
                    .bookType(data[4])
                    .build();

            bookRepository.save(bookEntity);
        }
    }
}
