package com.metadata.registration.api;

import com.metadata.registration.api.dto.StudentDto;
import com.metadata.registration.service.model.Student;
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

    @GetMapping("/student")
    public ResponseEntity<List<StudentDto>> getStudents() {

        studentRepository.save(Student.builder().name("Oco").age(16).build());

        return ResponseEntity.ok(Arrays.asList(
           StudentDto.builder().name("Pepe").age(14).build(),
           StudentDto.builder().name("Angela").age(18).build()
        ));
    }
}
