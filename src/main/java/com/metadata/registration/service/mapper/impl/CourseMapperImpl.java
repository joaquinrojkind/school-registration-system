package com.metadata.registration.service.mapper.impl;

import com.metadata.registration.api.dto.CourseDto;
import com.metadata.registration.api.dto.StudentDto;
import com.metadata.registration.persistence.model.Course;
import com.metadata.registration.persistence.model.Student;
import com.metadata.registration.service.mapper.CourseMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseDto mapToCourseDto(Course course) {
        return CourseDto.builder()
                .id(course.getUuid())
                .title(course.getTitle())
                .teacher(course.getTeacher())
                .students(mapToStudentDtoList(course.getStudents()))
                .build();
    }

    @Override
    public List<CourseDto> mapToCourseDtoList(List<Course> courses) {
        return courses.stream()
                .map(this::mapToCourseDto)
                .collect(Collectors.toList());
    }

    private StudentDto mapToStudentDto(Student student) {
        return StudentDto.builder()
                .id(student.getUuid())
                .name(student.getName())
                .age(student.getAge())
                .build();
    }

    private List<StudentDto> mapToStudentDtoList(List<Student> students) {
        return students.stream()
                .map(this::mapToStudentDto)
                .collect(Collectors.toList());
    }
}
