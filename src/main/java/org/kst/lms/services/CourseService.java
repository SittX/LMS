package org.kst.lms.services;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.CourseDTO;
import org.kst.lms.exceptions.ResourceNotFoundException;
import org.kst.lms.mappers.CourseMapper;
import org.kst.lms.models.Course;
import org.kst.lms.repositories.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public Page<Course> findAll(int page, int size, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return this.courseRepository.findAll(pageRequest);
    }

    public List<Course> findByIds(Collection<Long> courseIds){
        return this.courseRepository.findAllById(courseIds);
    }

    public Course findById(long courseId) {
        return this.courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course with given ID cannot be found."));
    }

    public List<Course> findByRegistrationId(long registrationId) {
        return this.courseRepository.findByRegistrations_Id(registrationId);
    }

    public CourseDTO save(CourseDTO courseDTO) {
        Course course = this.courseMapper.toEntity(courseDTO);
        return this.courseMapper.toDTO(this.courseRepository.save(course));
    }
}
