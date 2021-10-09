package com.ms.printing.bookprint.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PrintServiceException extends RuntimeException {

    private HttpStatus httpStatus;

    public PrintServiceException() {
    }

    public PrintServiceException(String message) {
        super(message);
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public PrintServiceException(String message, Throwable cause) {
        super(message, cause);
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public PrintServiceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public PrintServiceException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }
}
