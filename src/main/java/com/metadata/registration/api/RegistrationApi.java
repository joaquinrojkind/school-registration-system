package com.metadata.registration.api;

import com.metadata.registration.api.dto.StudentDto;
import com.metadata.registration.service.model.Course;
import com.metadata.registration.service.model.Student;
import com.metadata.registration.service.repository.CourseRepository;
import com.metadata.registration.service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController("/")
public class RegistrationApi {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/student")
    public ResponseEntity<List<StudentDto>> getStudents() {

        Course philosophyCourse = Course.builder()
                .name("Philosophy")
                .teacher("Adam Wolf")
                .build();
        courseRepository.save(philosophyCourse);

        Course mathCourse = Course.builder()
                .name("Math")
                .teacher("Greta Ula")
                .build();
        courseRepository.save(mathCourse);

        Student student = Student.builder()
                .name("Oco")
                .age(16)
                .courses(Arrays.asList(philosophyCourse, mathCourse))
                .build();
        studentRepository.save(student);

        return ResponseEntity.ok(Arrays.asList(
           StudentDto.builder().name("Pepe").age(14).build(),
           StudentDto.builder().name("Angela").age(18).build()
        ));
    }
}
