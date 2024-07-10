package org.kst.lms.repositories;

import org.kst.lms.models.CourseModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<CourseModule, Long> {
}
