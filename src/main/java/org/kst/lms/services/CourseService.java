package org.kst.lms.services;

import lombok.RequiredArgsConstructor;
import org.kst.lms.exceptions.ResourceNotFoundException;
import org.kst.lms.models.Course;
import org.kst.lms.models.User;
import org.kst.lms.repositories.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

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

    public Course findById(long courseId) {
        Course course = this.courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course with given ID cannot be found."));
        return course;
    }

    public List<Course> findByRegistrationId(long registrationId) {
        return this.courseRepository.findByRegistrations_Id(registrationId);
    }

    public Course save(Course course) {
        Course savedCourse = this.courseRepository.save(course);
        return savedCourse;
    }

    public Course update(long id, Course course){
        Course oldCourse = this.courseRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Course with the given name cannot be found."));
        oldCourse.setTitle(course.getTitle());
        oldCourse.setDescription(course.getDescription());
        oldCourse.setStartDate(course.getStartDate());
        oldCourse.setEndDate(course.getEndDate());
        Course updatedCourse = this.courseRepository.save(oldCourse);
        return updatedCourse;
    }

    public List<User> getCourseUser(long courseId){
        Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course with given Id cannot be found"));
        return course.getUsers();
    }
}
