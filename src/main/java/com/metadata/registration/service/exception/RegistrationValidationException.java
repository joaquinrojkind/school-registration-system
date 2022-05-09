package com.metadata.registration.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegistrationValidationException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;
}
