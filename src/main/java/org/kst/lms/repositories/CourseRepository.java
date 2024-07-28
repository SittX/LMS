package org.kst.lms.repositories;

import org.kst.lms.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByRegistrations_Id(Long registrations_id);
}
