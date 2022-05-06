package com.metadata.registration.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CourseDto {

    private String uuid;
    private String title;
    private String teacher;
    private List<StudentDto> students;
}
