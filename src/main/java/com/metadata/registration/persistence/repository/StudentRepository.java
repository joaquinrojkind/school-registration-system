package com.metadata.registration.persistence.repository;

import com.metadata.registration.persistence.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByUuid(String uuid);

    @Query(value = "select * from students s where (select count(*) from students_courses where student_id = s.id) = 0", nativeQuery = true)
    List<Student> findByEmptyCourses();

    @Query(value = "select * from students s \n" +
            "join students_courses sc on s.id = sc.student_id\n" +
            "join courses c on c.id = sc.course_id where c.uuid = :courseUuid ",
            nativeQuery = true)
    List<Student> findByCourseUuid(@Param("courseUuid") String courseUuid);
}
