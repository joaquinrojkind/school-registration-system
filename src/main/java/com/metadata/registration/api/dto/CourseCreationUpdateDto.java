package com.metadata.registration.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseCreationUpdateDto {

    private String title;
    private String teacher;
}
