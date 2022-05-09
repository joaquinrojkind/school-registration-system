package com.metadata.registration.service.impl;

import com.metadata.registration.api.dto.RegistrationRequestDto;
import com.metadata.registration.persistence.model.Course;
import com.metadata.registration.persistence.model.Student;
import com.metadata.registration.persistence.repository.CourseRepository;
import com.metadata.registration.persistence.repository.StudentRepository;
import com.metadata.registration.service.RegistrationService;
import com.metadata.registration.service.exception.ErrorCode;
import com.metadata.registration.service.exception.RegistrationValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    protected static final int MAX_COURSES_PER_STUDENT = 5;
    protected static final int MAX_STUDENTS_PER_COURSE = 50;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void registerToCourse(RegistrationRequestDto registrationRequestDto) {
        Student student = studentRepository.findByUuid(registrationRequestDto.getStudentId());
        Course course = courseRepository.findByUuid(registrationRequestDto.getCourseId());
        validateRegistration(student, course);
        student.getCourses().add(course);
        studentRepository.save(student);
    }

    private void validateRegistration(Student student, Course course) {
        if (student.getCourses().size() >= MAX_COURSES_PER_STUDENT) {
            throw new RegistrationValidationException(ErrorCode.MAX_COURSES_EXCEEDED, "Maximum number of courses per student has been reached (" + MAX_COURSES_PER_STUDENT + ")");
        }
        if (course.getStudents().size() >= MAX_STUDENTS_PER_COURSE) {
            throw new RegistrationValidationException(ErrorCode.MAX_STUDENTS_EXCEEDED, "Maximum number of students per course has been reached (" + MAX_STUDENTS_PER_COURSE + ")");
        }
    }
}
