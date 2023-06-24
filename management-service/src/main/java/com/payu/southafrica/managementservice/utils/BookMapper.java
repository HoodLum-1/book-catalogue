package com.payu.southafrica.managementservice.utils;

import com.payu.southafrica.managementservice.dto.BookDto;
import com.payu.southafrica.managementservice.persistence.BookEntity;


public class BookMapper {

    public static BookEntity mapToEntity(BookDto bookDto) {
        return BookEntity.builder()
                .id(bookDto.getId())
                .name(bookDto.getName())
                .isbn(bookDto.getIsbn())
                .publishDate(bookDto.getPublishDate())
                .price(bookDto.getPrice())
                .bookType(bookDto.getBookType())
                .build();
    }

    public static BookDto mapToDto(BookEntity bookEntity) {
        return  BookDto.builder()
                .id(bookEntity.getId())
                .name(bookEntity.getName())
                .isbn(bookEntity.getIsbn())
                .publishDate(bookEntity.getPublishDate())
                .price(bookEntity.getPrice())
                .bookType(bookEntity.getBookType())
                .build();
    }
}
