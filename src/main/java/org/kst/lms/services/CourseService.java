package org.kst.lms.services;

import lombok.RequiredArgsConstructor;
import org.kst.lms.models.Course;
import org.kst.lms.repositories.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public Course save(Course course) {
       return this.courseRepository.save(course);
    }

    public Page<Course> findAll(int size, int page) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return this.courseRepository.findAll(pageRequest);
    }

    public List<Course> findByRegistrationId(long registrationId) {
        return this.courseRepository.findByRegistrations_Id(registrationId);
    }
}
