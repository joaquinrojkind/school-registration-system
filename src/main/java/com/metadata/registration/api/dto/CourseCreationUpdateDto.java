package com.metadata.registration.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CourseCreationUpdateDto {

    private String title;
    private String teacher;
}
