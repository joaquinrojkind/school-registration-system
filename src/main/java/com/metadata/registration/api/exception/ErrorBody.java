package com.metadata.registration.api.exception;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class ErrorBody {

    private int status;
    private String errorCode;
    private String message;
}
