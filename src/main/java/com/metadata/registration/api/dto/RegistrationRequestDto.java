package com.metadata.registration.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RegistrationRequestDto {

    private String studentId;
    private String courseId;
}
