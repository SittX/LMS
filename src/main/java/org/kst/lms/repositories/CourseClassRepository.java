package org.kst.lms.repositories;

import org.kst.lms.models.CourseClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseClassRepository extends JpaRepository<CourseClass, Long> {
}
