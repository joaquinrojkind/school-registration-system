package com.metadata.registration.service.impl;

import com.metadata.registration.api.dto.CourseCreationUpdateDto;
import com.metadata.registration.api.dto.CourseDto;
import com.metadata.registration.persistence.model.Course;
import com.metadata.registration.persistence.model.Student;
import com.metadata.registration.persistence.repository.CourseRepository;
import com.metadata.registration.service.CourseService;
import com.metadata.registration.service.mapper.CourseMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMapper courseMapper;

    @Override
    @Transactional
    public CourseDto createCourse(CourseCreationUpdateDto courseCreationUpdateDto) {
        // No students can be assigned upon creation, there´s a specific api for that
        Course course = Course.builder()
                .uuid(UUID.randomUUID().toString())
                .title(courseCreationUpdateDto.getTitle())
                .teacher(courseCreationUpdateDto.getTeacher())
                .students(Collections.emptyList())
                .build();
        return courseMapper.mapToCourseDto(courseRepository.save(course));
    }

    @Override
    @Transactional
    public CourseDto updateCourse(String uuid, CourseCreationUpdateDto courseCreationUpdateDto) {
        // No students can be assigned upon update, there´s a specific api for that
        Course course = courseRepository.findByUuid(uuid);
        course.setTitle(courseCreationUpdateDto.getTitle());
        course.setTeacher(courseCreationUpdateDto.getTeacher());
        return courseMapper.mapToCourseDto(courseRepository.save(course));
    }

    @Override
    @Transactional
    public void deleteCourse(String uuid) {
        Course course = courseRepository.findByUuid(uuid);
        courseRepository.delete(course);
    }

    @Override
    public CourseDto getCourse(String uuid) {
        return courseMapper.mapToCourseDto(courseRepository.findByUuid(uuid));
    }

    @Override
    public List<CourseDto> getCourses(String studentUuid, boolean emptyStudents) {
        if (emptyStudents) {
            return courseMapper.mapToCourseDtoList(courseRepository.findByEmptyStudents());
        }
        if (!StringUtils.isEmpty(studentUuid)) {
            return courseMapper.mapToCourseDtoList(courseRepository.findByStudentUuid(studentUuid));
        }
        return courseMapper.mapToCourseDtoList(courseRepository.findAll()); // implement pagination
    }
}
