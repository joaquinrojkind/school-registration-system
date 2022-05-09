package com.metadata.registration.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentCreationUpdateDto {

    private String name;
    private Integer age;
}
