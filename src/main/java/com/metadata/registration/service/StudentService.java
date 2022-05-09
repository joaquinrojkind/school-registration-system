package com.metadata.registration.service;

import com.metadata.registration.api.dto.StudentCreationUpdateDto;
import com.metadata.registration.api.dto.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto createStudent(StudentCreationUpdateDto studentCreationUpdateDto);

    StudentDto updateStudent(String uuid, StudentCreationUpdateDto studentCreationUpdateDto);

    void deleteStudent(String uuid);

    StudentDto getStudent(String uuid);

    /**
     * Get a student list using optional filters
     * @param courseUuid optional filter
     * @param emptyCourses only students without any courses
     * @return
     */
    List<StudentDto> getStudents(String courseUuid, boolean emptyCourses);
}
