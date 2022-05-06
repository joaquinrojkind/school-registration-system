package com.metadata.registration.service.impl;

import com.metadata.registration.api.dto.CourseDto;
import com.metadata.registration.api.dto.RegistrationRequestDto;
import com.metadata.registration.api.dto.StudentDto;
import com.metadata.registration.persistence.model.Course;
import com.metadata.registration.persistence.model.Student;
import com.metadata.registration.persistence.repository.CourseRepository;
import com.metadata.registration.persistence.repository.StudentRepository;
import com.metadata.registration.service.SchoolRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SchoolRegistrationServiceImpl implements SchoolRegistrationService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;


    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        // Larger entities with many fields could justify the implementation of a mapper
        // No courses can be assigned upon creation, there´s a specific api for that
        Student student = Student.builder()
                .uuid(UUID.randomUUID().toString())
                .name(studentDto.getName())
                .age(studentDto.getAge())
                .build();
        studentRepository.save(student);
        return StudentDto.builder()
                .uuid(student.getUuid())
                .name(student.getName())
                .age(student.getAge())
                .courses(Collections.emptyList())
                .build();
    }

    @Override
    public StudentDto updateStudent(String uuid, StudentDto studentDto) {
        Student student = studentRepository.findByUuid(uuid);
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        student = studentRepository.save(student);
        return StudentDto.builder()
                .uuid(student.getUuid())
                .name(student.getName())
                .age(student.getAge())
                .courses(mapToCoursesDto(student.getCourses()))
                .build();
    }

    @Override
    public void deleteStudent(String uuid) {
        studentRepository.deleteByUuid(uuid);
    }

    @Override
    public StudentDto getStudent(String uuid) {
        Student student = studentRepository.findByUuid(uuid);
        return StudentDto.builder()
                .uuid(student.getUuid())
                .name(student.getName())
                .age(student.getAge())
                .courses(mapToCoursesDto(student.getCourses()))
                .build();
    }

    @Override
    public List<StudentDto> getStudents(String courseUuid, Boolean emptyCourses) {
        if (emptyCourses) {
            List<Student> students = studentRepository.findByEmptyCourses();
            return students.stream()
                    .map(student -> StudentDto.builder()
                            .uuid(student.getUuid())
                            .name(student.getName())
                            .age(student.getAge())
                            .courses(Collections.emptyList())
                            .build())
                    .collect(Collectors.toList());
        }
        if (!StringUtils.isEmpty(courseUuid)) {
            List<Student> students = studentRepository.findByCourseUuid(courseUuid);
            return students.stream()
                    .map(student -> StudentDto.builder()
                            .uuid(student.getUuid())
                            .name(student.getName())
                            .age(student.getAge())
                            .courses(mapToCoursesDto(student.getCourses()))
                            .build())
                    .collect(Collectors.toList());
        }
        List<Student> students = studentRepository.findAll(); // implement pagination
        return students.stream()
                .map(student -> StudentDto.builder()
                        .uuid(student.getUuid())
                        .name(student.getName())
                        .age(student.getAge())
                        .courses(mapToCoursesDto(student.getCourses()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        // Larger entities with many fields could justify the implementation of a mapper
        // No courses can be assigned upon creation, there´s a specific api for that
        Course course = Course.builder()
                .uuid(UUID.randomUUID().toString())
                .title(courseDto.getTitle())
                .teacher(courseDto.getTeacher())
                .build();
        courseRepository.save(course);
        return CourseDto.builder()
                .uuid(course.getUuid())
                .title(course.getTitle())
                .teacher(course.getTeacher())
                .students(Collections.emptyList())
                .build();
    }

    @Override
    public void registerToCourse(RegistrationRequestDto registrationRequestDto) {
        Student student = studentRepository.findByUuid(registrationRequestDto.getStudentId());
        Course course = courseRepository.findByUuid(registrationRequestDto.getCourseId());
        student.getCourses().add(course);
        studentRepository.save(student);
    }

    List<CourseDto> mapToCoursesDto(List<Course> courses) {
        return courses.stream()
                .map(course -> CourseDto.builder()
                        .uuid(course.getUuid())
                        .title(course.getTitle())
                        .teacher(course.getTeacher())
                        .build())
                .collect(Collectors.toList());
    }
}
