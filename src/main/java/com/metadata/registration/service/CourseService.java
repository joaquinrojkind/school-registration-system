package com.metadata.registration.service;

import com.metadata.registration.api.dto.CourseCreationUpdateDto;
import com.metadata.registration.api.dto.CourseDto;

import java.util.List;

public interface CourseService {

    CourseDto createCourse(CourseCreationUpdateDto courseCreationUpdateDto);

    CourseDto updateCourse(String uuid, CourseCreationUpdateDto courseCreationUpdateDto);

    void deleteCourse(String uuid);

    CourseDto getCourse(String uuid);

    List<CourseDto> getCourses(String studentUuid, boolean emptyStudents);
}
