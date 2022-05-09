package com.metadata.registration.api;

import com.metadata.registration.api.dto.*;
import com.metadata.registration.service.CourseService;
import com.metadata.registration.service.RegistrationService;
import com.metadata.registration.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registration-system")
public class RegistrationApi {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    @PostMapping("/student")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentCreationUpdateDto studentCreationUpdateDto) {
        return ResponseEntity.ok(studentService.createStudent(studentCreationUpdateDto));
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("id") String uuid, @RequestBody StudentCreationUpdateDto studentCreationUpdateDto) {
        return ResponseEntity.ok(studentService.updateStudent(uuid, studentCreationUpdateDto));
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity deleteStudent(@PathVariable("id") String uuid) {
        studentService.deleteStudent(uuid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable("id") String uuid) {
        return ResponseEntity.ok(studentService.getStudent(uuid));
    }

    @GetMapping("/student")
    public ResponseEntity<List<StudentDto>> getStudents(@RequestParam(value = "courseId", required = false) String courseUuid, @RequestParam(value = "emptyCourses", required = false) boolean emptyCourses) {
        return ResponseEntity.ok(studentService.getStudents(courseUuid, emptyCourses));
    }

    @PostMapping("/course")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseCreationUpdateDto courseCreationUpdateDto) {
        return ResponseEntity.ok(courseService.createCourse(courseCreationUpdateDto));
    }

    @PutMapping("/course/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable("id") String uuid, @RequestBody CourseCreationUpdateDto courseCreationUpdateDto) {
        return ResponseEntity.ok(courseService.updateCourse(uuid, courseCreationUpdateDto));
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity deleteCourse(@PathVariable("id") String uuid) {
        courseService.deleteCourse(uuid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable("id") String uuid) {
        return ResponseEntity.ok(courseService.getCourse(uuid));
    }

    @GetMapping("/course")
    public ResponseEntity<List<CourseDto>> getCourses(@RequestParam(value = "studentId", required = false) String studentUuid, @RequestParam(value = "emptyStudents", required = false) boolean emptyStudents) {
        return ResponseEntity.ok(courseService.getCourses(studentUuid, emptyStudents));
    }

    @PostMapping("/registration")
    public ResponseEntity registerToCourse(@RequestBody RegistrationRequestDto registrationRequestDto) {
        registrationService.registerToCourse(registrationRequestDto);
        return ResponseEntity.noContent().build();
    }
}
