package com.metadata.registration.service;

import com.metadata.registration.api.dto.CourseDto;
import com.metadata.registration.api.dto.RegistrationRequestDto;
import com.metadata.registration.api.dto.StudentDto;

import java.util.List;

public interface SchoolRegistrationService {

    StudentDto createStudent(StudentDto studentDto);

    StudentDto updateStudent(String uuid, StudentDto studentDto);

    void deleteStudent(String uuid);

    StudentDto getStudent(String uuid);

    List<StudentDto> getStudents(String courseUuid, Boolean emptyCourses);

    CourseDto createCourse(CourseDto courseDto);

    void registerToCourse(RegistrationRequestDto registrationRequestDto);
}
