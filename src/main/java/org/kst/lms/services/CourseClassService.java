package org.kst.lms.services;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.CourseClassRequest;
import org.kst.lms.exceptions.ResourceNotFoundException;
import org.kst.lms.mappers.CourseClassMapper;
import org.kst.lms.models.Course;
import org.kst.lms.models.CourseClass;
import org.kst.lms.repositories.CourseClassRepository;
import org.kst.lms.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseClassService {
    private final CourseClassRepository courseClassRepository;
    private final CourseRepository courseRepository;
    private final CourseClassMapper courseClassMapper;

    public CourseClass save(CourseClassRequest courseClassRequest){
        CourseClass courseClass = this.courseClassMapper.toEntity(courseClassRequest);
        return this.courseClassRepository.save(courseClass);
    }

    public List<CourseClass> findAll(){
       return this.courseClassRepository.findAll().stream()
               .sorted(Comparator.comparingLong(CourseClass::getId))
               .toList();
    }

    public CourseClass update(long id, CourseClassRequest courseClassRequest){
        CourseClass courseClass = this.courseClassRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Class with given id cannot be found."));
        Course course = this.courseRepository.findById(courseClassRequest.getCourseId()).orElseThrow(() -> new ResourceNotFoundException("Course with given Id cannot be found."));
        courseClass.setName(courseClassRequest.getName());
        courseClass.setDescription(courseClassRequest.getDescription());
        courseClass.setCourse(course);
        this.courseClassRepository.save(courseClass);
        return courseClass;
    }
}

