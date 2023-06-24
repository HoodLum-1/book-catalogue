package com.payu.southafrica.managementservice.services.exception;

import lombok.Getter;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus
public class BookNotFoundException extends RuntimeException {
    private final String message;

    public BookNotFoundException(String message) {
        this.message = message;
    }
}
