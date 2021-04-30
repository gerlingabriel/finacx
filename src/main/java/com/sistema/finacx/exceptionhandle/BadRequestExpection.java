package com.sistema.finacx.exceptionhandle;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestExpection extends RuntimeException {

    public BadRequestExpection(String message) {
        super(message);
    }

    
}
