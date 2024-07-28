package org.kst.lms.services;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.CourseClassRequest;
import org.kst.lms.dtos.SubjectSchedulesRequest;
import org.kst.lms.exceptions.ResourceNotFoundException;
import org.kst.lms.mappers.CourseClassMapper;
import org.kst.lms.models.Course;
import org.kst.lms.models.Subject;
import org.kst.lms.models.SubjectSchedule;
import org.kst.lms.models.User;
import org.kst.lms.models.enums.DaysOfWeek;
import org.kst.lms.repositories.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseClassMapper courseClassMapper;
    private final SubjectService subjectService;
    private final SubjectScheduleService subjectScheduleService;

    public Page<Course> findAll(int page, int size, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return this.courseRepository.findAll(pageRequest);
    }

    public List<Course> findAll(){
        return this.courseRepository.findAll()
                .stream()
                .sorted(Comparator.comparingLong(Course::getId))
                .toList();
    }

    public List<Course> findByIds(Collection<Long> courseIds) {
        return this.courseRepository.findAllById(courseIds);
    }

    public Course findById(Long courseId) {
        return this.courseRepository.findById(courseId)
                .orElseThrow(()-> new ResourceNotFoundException("Course not found with id: " + courseId));
    }

    public Course findById(long courseId) {
        return this.courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course with given ID cannot be found."));
    }

    public List<Course> findByRegistrationId(long registrationId) {
        return this.courseRepository.findByRegistrations_Id(registrationId);
    }

    public Course save(CourseClassRequest request){
        Course course = this.courseClassMapper.mapToEntity(request);
        Course savedCourse = this.courseRepository.save(course);

        // TODO : Need to check properly
        // SubjectSchedules should be persisted first before saving it to the Course
        List<SubjectSchedule> subjectSchedules = request.getSubjectSchedules().stream().map(s -> {
            Set<DaysOfWeek> schedules = s.getDaysOfWeek().stream()
                    .map(DaysOfWeek::findByValue)
                    .collect(Collectors.toSet());
            Subject subject = this.subjectService.findById(s.getSubjectId());
            return SubjectSchedule
                    .builder()
                    .subject(subject)
                    .schedules(schedules)
                    .course(savedCourse)
                    .build();
        }).toList();

        // Persist each Subject Schedule before setting to the course.
        // TODO: SubjectSchedule should have reference to the course.
        subjectSchedules.forEach(this.subjectScheduleService::save);

        return savedCourse;
    }

    public Course update(long id, Course course){
        Course oldCourse = this.courseRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Course with the given name cannot be found."));
        oldCourse.setDescription(course.getDescription());
        oldCourse.setStartDate(course.getStartDate());
        oldCourse.setEndDate(course.getEndDate());
        oldCourse.setSubjectSchedules(course.getSubjectSchedules());

        return this.courseRepository.save(oldCourse);
    }

    public List<User> getCourseUser(long courseId){
        Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course with given Id cannot be found"));
        return course.getUsers();
    }
}
