package org.kst.lms.repositories;

import org.kst.lms.models.CourseClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseClassRepository extends JpaRepository<CourseClass, Long> {
    List<CourseClass> findByRegistrations_Id(Long registrations_id);
}
