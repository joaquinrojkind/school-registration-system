package com.metadata.registration.api.exception;

import com.metadata.registration.service.exception.RegistrationValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RegistrationExceptionHandler {

    @ExceptionHandler(RegistrationValidationException.class)
    public ResponseEntity<ErrorBody> registrationValidationException(RegistrationValidationException ex, WebRequest request) {
        ErrorBody errorBody = ErrorBody.builder()
                .errorCode(ex.getErrorCode().name())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }
}
