package com.metadata.registration.service.impl;

import com.metadata.registration.api.dto.RegistrationRequestDto;
import com.metadata.registration.persistence.model.Course;
import com.metadata.registration.persistence.model.Student;
import com.metadata.registration.persistence.repository.CourseRepository;
import com.metadata.registration.persistence.repository.StudentRepository;
import com.metadata.registration.service.exception.ErrorCode;
import com.metadata.registration.service.exception.RegistrationValidationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.metadata.registration.service.impl.RegistrationServiceImpl.MAX_COURSES_PER_STUDENT;
import static com.metadata.registration.service.impl.RegistrationServiceImpl.MAX_STUDENTS_PER_COURSE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class RegistrationServiceImplTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterToCourse_ok() {

        final String studentId = UUID.randomUUID().toString();
        final String courseId = UUID.randomUUID().toString();

        List<Course> previousCourses = new ArrayList<>();
        previousCourses.add(Course.builder().uuid(UUID.randomUUID().toString()).build());
        Student student = Student.builder()
                .uuid(studentId)
                .courses(previousCourses)
                .build();

        List<Student> previousStudents = new ArrayList<>();
        previousStudents.add(Student.builder().uuid(UUID.randomUUID().toString()).build());
        Course course = Course.builder()
                .uuid(courseId)
                .students(previousStudents)
                .build();

        RegistrationRequestDto registrationRequestDto = RegistrationRequestDto.builder()
                .studentId(studentId)
                .courseId(courseId)
                .build();

        when(studentRepository.findByUuid(anyString())).thenReturn(student);
        when(courseRepository.findByUuid(anyString())).thenReturn(course);

        registrationService.registerToCourse(registrationRequestDto);

        ArgumentCaptor<Student> studentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository, times(1)).save(studentCaptor.capture());
        Student registeredStudent = studentCaptor.getValue();

        assertThat(registeredStudent.getCourses().contains(course));
    }

    @Test
    public void testRegisterToCourse_studentExceedsAllowedAmountOfCourses() {

        final String studentId = UUID.randomUUID().toString();
        final String courseId = UUID.randomUUID().toString();

        List<Course> previousCourses = new ArrayList<>();
        for (int i = 0; i < MAX_COURSES_PER_STUDENT; i++) {
            previousCourses.add(Course.builder().uuid(UUID.randomUUID().toString()).build());
        }
        Student student = Student.builder()
                .uuid(studentId)
                .courses(previousCourses)
                .build();

        Course course = Course.builder()
                .uuid(courseId)
                .students(Collections.emptyList())
                .build();

        RegistrationRequestDto registrationRequestDto = RegistrationRequestDto.builder()
                .studentId(studentId)
                .courseId(courseId)
                .build();

        when(studentRepository.findByUuid(anyString())).thenReturn(student);
        when(courseRepository.findByUuid(anyString())).thenReturn(course);

        assertThatThrownBy(() -> registrationService.registerToCourse(registrationRequestDto))
                .isInstanceOf(RegistrationValidationException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.MAX_COURSES_EXCEEDED);
    }

    @Test
    public void testRegisterToCourse_courseExceedsAllowedAmountOfStudents() {

        final String studentId = UUID.randomUUID().toString();
        final String courseId = UUID.randomUUID().toString();

        List<Student> previousStudents = new ArrayList<>();
        for (int i = 0; i < MAX_STUDENTS_PER_COURSE; i++) {
            previousStudents.add(Student.builder().uuid(UUID.randomUUID().toString()).build());
        }
        Course course = Course.builder()
                .uuid(courseId)
                .students(previousStudents)
                .build();

        Student student = Student.builder()
                .uuid(studentId)
                .courses(Collections.emptyList())
                .build();

        RegistrationRequestDto registrationRequestDto = RegistrationRequestDto.builder()
                .studentId(studentId)
                .courseId(courseId)
                .build();

        when(studentRepository.findByUuid(anyString())).thenReturn(student);
        when(courseRepository.findByUuid(anyString())).thenReturn(course);

        assertThatThrownBy(() -> registrationService.registerToCourse(registrationRequestDto))
                .isInstanceOf(RegistrationValidationException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.MAX_STUDENTS_EXCEEDED);
    }
}
