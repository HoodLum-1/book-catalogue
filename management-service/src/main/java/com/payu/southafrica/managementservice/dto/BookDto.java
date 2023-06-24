package com.payu.southafrica.managementservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookDto {
    private Long id;
    private String name;
    private String isbn;
    private LocalDate publishDate;
    private double price;
    private String bookType;
}
