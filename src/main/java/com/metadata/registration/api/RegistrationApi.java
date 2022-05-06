package com.metadata.registration.api;

import com.metadata.registration.api.dto.CourseDto;
import com.metadata.registration.api.dto.RegistrationRequestDto;
import com.metadata.registration.api.dto.StudentDto;
import com.metadata.registration.service.SchoolRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/")
public class RegistrationApi {

    @Autowired
    private SchoolRegistrationService schoolRegistrationService;

    @PostMapping("/student")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(schoolRegistrationService.createStudent(studentDto));
    }

    @PatchMapping("/student/{id}")
    public ResponseEntity<StudentDto> updateStudent(@RequestParam("id") String uuid, @RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(schoolRegistrationService.updateStudent(uuid, studentDto));
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity deleteStudent(@RequestParam("id") String uuid, @RequestBody StudentDto studentDto) {
        schoolRegistrationService.deleteStudent(uuid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDto> getStudent(@RequestParam("id") String uuid) {
        return ResponseEntity.ok(schoolRegistrationService.getStudent(uuid));
    }

    @GetMapping("/student")
    public ResponseEntity<List<StudentDto>> getStudents(@RequestParam("courseId") String courseUuid, @RequestParam("emptyCourses") Boolean emptyCourses) {
        return ResponseEntity.ok(schoolRegistrationService.getStudents(courseUuid, emptyCourses));
    }

    @PostMapping("/course")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(schoolRegistrationService.createCourse(courseDto));
    }

    @PostMapping("/course/registration")
    public ResponseEntity registerToCourse(@RequestBody RegistrationRequestDto registrationRequestDto) {
        schoolRegistrationService.registerToCourse(registrationRequestDto);
        return ResponseEntity.noContent().build();
    }
}
