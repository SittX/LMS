package org.kst.lms.services;

import lombok.RequiredArgsConstructor;
import org.kst.lms.models.SubjectSchedule;
import org.kst.lms.repositories.SubjectScheduleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectScheduleService {
    private final SubjectScheduleRepository subjectScheduleRepository;

    public SubjectSchedule save(final SubjectSchedule subjectSchedule){
        return this.subjectScheduleRepository.save(subjectSchedule);
    }
}
