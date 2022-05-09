package com.metadata.registration.service.mapper;

import com.metadata.registration.api.dto.CourseDto;
import com.metadata.registration.persistence.model.Course;

import java.util.List;

public interface CourseMapper {

    CourseDto mapToCourseDto(Course course);

    List<CourseDto> mapToCourseDtoList(List<Course> courses);
}
