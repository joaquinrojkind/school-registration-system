package com.metadata.registration.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {

    private String id;
    private String name;
    private Integer age;
    private List<CourseDto> courses;
}
