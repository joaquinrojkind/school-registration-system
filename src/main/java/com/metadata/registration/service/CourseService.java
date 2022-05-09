package com.metadata.registration.service;

import com.metadata.registration.api.dto.CourseCreationUpdateDto;
import com.metadata.registration.api.dto.CourseDto;

import java.util.List;

public interface CourseService {

    CourseDto createCourse(CourseCreationUpdateDto courseCreationUpdateDto);

    CourseDto updateCourse(String uuid, CourseCreationUpdateDto courseCreationUpdateDto);

    void deleteCourse(String uuid);

    CourseDto getCourse(String uuid);

    /**
     * Get a course list using optional filters
     * @param studentUuid optional filter
     * @param emptyStudents only courses without any students
     * @return
     */
    List<CourseDto> getCourses(String studentUuid, boolean emptyStudents);
}
