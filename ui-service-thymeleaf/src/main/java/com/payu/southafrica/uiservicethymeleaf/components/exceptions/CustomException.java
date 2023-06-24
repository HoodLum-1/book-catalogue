package com.payu.southafrica.uiservicethymeleaf.components.exceptions;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
