package org.kst.lms.mappers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.CourseClassRequest;
import org.kst.lms.models.Course;
import org.kst.lms.models.Subject;
import org.kst.lms.models.SubjectSchedule;
import org.kst.lms.models.User;
import org.kst.lms.models.enums.DaysOfWeek;
import org.kst.lms.repositories.UserRepository;
import org.kst.lms.services.SubjectScheduleService;
import org.kst.lms.services.SubjectService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CourseClassMapper {
//    private final UserService userService;
    private final UserRepository userRepository;
    private final SubjectService subjectService;
    private final SubjectScheduleService subjectScheduleService;

    public Course mapToEntity(CourseClassRequest request) {
       Course course = new Course();
       course.setName(request.getName());
       course.setDescription(request.getDescription());
       course.setStartDate(request.getStartDate());
       course.setEndDate(request.getEndDate());

       // TODO : we should not directly access to the user repository.
        // But I have done it here because there is a circular dependency happening between this class and User Service.
        // Spring boot prohibits Circular Dependency and won't run the application due to this.
       User teacher = userRepository.findById(request.getTeacherId()).orElseThrow(() -> new NoSuchElementException("User Not found"));
       course.getUsers().add(teacher);
       return course;
    }
}
