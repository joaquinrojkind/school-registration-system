package com.metadata.registration.persistence.repository;

import com.metadata.registration.persistence.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByUuid(String uuid);

    @Query(value = "select * from courses c where (select count(*) from students_courses where course_id = c.id) = 0", nativeQuery = true)
    List<Course> findByEmptyStudents();

    @Query(value = "select * from courses c \n" +
            "join students_courses sc on c.id = sc.course_id\n" +
            "join students s on s.id = sc.student_id where s.uuid = :studentUuid ",
            nativeQuery = true)
    List<Course> findByStudentUuid(@Param("studentUuid") String studentUuid);

    Course deleteByUuid(String uuid);
}
