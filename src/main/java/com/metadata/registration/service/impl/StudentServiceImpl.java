package com.metadata.registration.service.impl;

import com.metadata.registration.api.dto.StudentCreationUpdateDto;
import com.metadata.registration.api.dto.StudentDto;
import com.metadata.registration.persistence.model.Student;
import com.metadata.registration.persistence.repository.StudentRepository;
import com.metadata.registration.service.StudentService;
import com.metadata.registration.service.mapper.StudentMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    @Transactional
    public StudentDto createStudent(StudentCreationUpdateDto studentCreationUpdateDto) {
        // No courses can be assigned upon creation, there´s a specific api for that
        Student student = Student.builder()
                .uuid(UUID.randomUUID().toString())
                .name(studentCreationUpdateDto.getName())
                .age(studentCreationUpdateDto.getAge())
                .courses(Collections.emptyList())
                .build();
        return studentMapper.mapToStudentDto(studentRepository.save(student));
    }

    @Override
    @Transactional
    public StudentDto updateStudent(String uuid, StudentCreationUpdateDto studentCreationUpdateDto) {
        // No courses can be assigned upon update, there´s a specific api for that
        Student student = studentRepository.findByUuid(uuid);
        student.setName(studentCreationUpdateDto.getName());
        student.setAge(studentCreationUpdateDto.getAge());
        return studentMapper.mapToStudentDto(studentRepository.save(student));
    }

    @Override
    @Transactional
    public void deleteStudent(String uuid) {
        Student student = studentRepository.findByUuid(uuid);
        studentRepository.delete(student);
    }

    @Override
    public StudentDto getStudent(String uuid) {
        return studentMapper.mapToStudentDto(studentRepository.findByUuid(uuid));
    }

    @Override
    public List<StudentDto> getStudents(String courseUuid, boolean emptyCourses) {
        if (emptyCourses) {
            return studentMapper.mapToStudentDtoList(studentRepository.findByEmptyCourses());
        }
        if (!StringUtils.isEmpty(courseUuid)) {
            return studentMapper.mapToStudentDtoList(studentRepository.findByCourseUuid(courseUuid));
        }
        return studentMapper.mapToStudentDtoList(studentRepository.findAll()); // implement pagination
    }
}
