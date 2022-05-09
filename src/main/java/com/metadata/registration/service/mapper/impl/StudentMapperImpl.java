package com.metadata.registration.service.mapper.impl;

import com.metadata.registration.api.dto.CourseDto;
import com.metadata.registration.api.dto.StudentDto;
import com.metadata.registration.persistence.model.Course;
import com.metadata.registration.persistence.model.Student;
import com.metadata.registration.service.mapper.StudentMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentDto mapToStudentDto(Student student) {
        return StudentDto.builder()
                .id(student.getUuid())
                .name(student.getName())
                .age(student.getAge())
                .courses(mapToCourseDtoList(student.getCourses()))
                .build();
    }

    @Override
    public List<StudentDto> mapToStudentDtoList(List<Student> students) {
        return students.stream()
                .map(this::mapToStudentDto)
                .collect(Collectors.toList());
    }

    private CourseDto mapToCourseDto(Course course) {
        return CourseDto.builder()
                .id(course.getUuid())
                .title(course.getTitle())
                .teacher(course.getTeacher())
                .build();
    }

    private List<CourseDto> mapToCourseDtoList(List<Course> courses) {
        return courses.stream()
                .map(this::mapToCourseDto)
                .collect(Collectors.toList());
    }
}
