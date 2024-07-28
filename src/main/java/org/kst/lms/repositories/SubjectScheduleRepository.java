package org.kst.lms.repositories;

import org.kst.lms.models.SubjectSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectScheduleRepository extends JpaRepository<SubjectSchedule, Long> {
}
