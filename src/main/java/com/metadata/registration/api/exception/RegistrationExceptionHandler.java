package com.metadata.registration.api.exception;

import com.metadata.registration.service.exception.RegistrationValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RegistrationExceptionHandler {

    @ExceptionHandler(RegistrationValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorBody registrationValidationException(RegistrationValidationException ex, WebRequest request) {
        ErrorBody errorBody = ErrorBody.builder()
                .errorCode(ex.getErrorCode().name())
                .message(ex.getMessage())
                .build();
        return errorBody;
    }
}
