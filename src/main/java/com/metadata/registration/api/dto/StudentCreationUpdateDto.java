package com.metadata.registration.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreationUpdateDto {

    private String name;
    private Integer age;
}
