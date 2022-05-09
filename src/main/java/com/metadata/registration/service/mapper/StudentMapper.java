package com.metadata.registration.service.mapper;

import com.metadata.registration.api.dto.StudentDto;
import com.metadata.registration.persistence.model.Student;

import java.util.List;

public interface StudentMapper {

    StudentDto mapToStudentDto(Student student);

    List<StudentDto> mapToStudentDtoList(List<Student> students);
}
