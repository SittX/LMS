package org.kst.lms.mappers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.CourseClassRequest;
import org.kst.lms.models.CourseClass;
import org.kst.lms.models.Subject;
import org.kst.lms.models.SubjectWithSchedule;
import org.kst.lms.models.User;
import org.kst.lms.models.enums.DaysOfWeek;
import org.kst.lms.services.SubjectService;
import org.kst.lms.services.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CourseClassMapper {
    private final UserService userService;
    private final SubjectService subjectService;

    public CourseClass mapToEntity(CourseClassRequest request) {
       CourseClass courseClass = new CourseClass();
       courseClass.setName(request.getName());
       courseClass.setDescription(request.getDescription());

       User teacher = userService.findById(request.getTeacherId());

        List<SubjectWithSchedule> subjectWithSchedules = request.getSubjectWithScheduleRequests().stream().map(s -> {
            Set<DaysOfWeek> schedules = s.getDaysOfWeek().stream()
                    .map(DaysOfWeek::findByValue)
                    .collect(Collectors.toSet());

            Subject subject = this.subjectService.findById(s.getSubjectId());

            return SubjectWithSchedule.builder().subject(subject).schedules(schedules).build();
        }).toList();

       courseClass.getUsers().add(teacher);
       courseClass.setSubjectWithSchedules(subjectWithSchedules);
        return courseClass;
    }
}
