package com.metadata.registration.persistence.repository;

import com.metadata.registration.persistence.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByUuid(String uuid);
}
