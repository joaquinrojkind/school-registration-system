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

//    @Query("select s from Student s join students_courses sc on s.id != sc.student_id")
    @Query("select s from Student s join Course c on s.id != c.id")
//    @Query(value = "select * from students s join students_courses sc on s.id != sc.student_id", nativeQuery = true)
    List<Student> findByEmptyCourses();

//    @Query("select s from students s join students_courses sc on s.id != sc.student_id where sc.course_id = :uuid")
    @Query("select s from Student s join Course c on s.id = c.id where c.id = :uuid")
    List<Student> findByCourseUuid(@Param("uuid") String courseUuid);

    Student deleteByUuid(String uuid);
}
