package org.kst.lms.services;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.CourseClassRequest;
import org.kst.lms.exceptions.ResourceNotFoundException;
import org.kst.lms.mappers.CourseClassMapper;
import org.kst.lms.models.CourseClass;
import org.kst.lms.models.User;
import org.kst.lms.repositories.CourseClassRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseClassService {
    private final CourseClassRepository courseClassRepository;
    private final CourseClassMapper courseClassMapper;

    public Page<CourseClass> findAll(int page, int size, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return this.courseClassRepository.findAll(pageRequest);
    }

    public List<CourseClass> findAll(){
        return this.courseClassRepository.findAll()
                .stream()
                .sorted(Comparator.comparingLong(CourseClass::getId))
                .toList();
    }

    public List<CourseClass> findByIds(Collection<Long> courseIds) {
        return this.courseClassRepository.findAllById(courseIds);
    }

    public CourseClass findById(Long courseId) {
        return this.courseClassRepository.findById(courseId)
                .orElseThrow(()-> new ResourceNotFoundException("Course not found with id: " + courseId));
    }

    public CourseClass findById(long courseId) {
        return this.courseClassRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course with given ID cannot be found."));
    }

    public List<CourseClass> findByRegistrationId(long registrationId) {
        return this.courseClassRepository.findByRegistrations_Id(registrationId);
    }

    public CourseClass save(CourseClassRequest courseClassRequest){
        CourseClass courseClass = this.courseClassMapper.mapToEntity(courseClassRequest);
        return this.courseClassRepository.save(courseClass);
    }

    public CourseClass update(long id, CourseClass courseClass){
        CourseClass oldCourseClass = this.courseClassRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Course with the given name cannot be found."));
        oldCourseClass.setDescription(courseClass.getDescription());
        oldCourseClass.setStartDate(courseClass.getStartDate());
        oldCourseClass.setEndDate(courseClass.getEndDate());
        oldCourseClass.setSubjectWithSchedules(courseClass.getSubjectWithSchedules());

        return this.courseClassRepository.save(oldCourseClass);
    }

    public List<User> getCourseUser(long courseId){
        CourseClass aCourseClass = this.courseClassRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course with given Id cannot be found"));
        return aCourseClass.getUsers();
    }
}
