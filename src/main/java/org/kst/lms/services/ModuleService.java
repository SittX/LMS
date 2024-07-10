package org.kst.lms.services;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.CourseModuleRequest;
import org.kst.lms.exceptions.ResourceNotFoundException;
import org.kst.lms.models.Course;
import org.kst.lms.models.CourseModule;
import org.kst.lms.repositories.CourseRepository;
import org.kst.lms.repositories.ModuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleService {
    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;

    public CourseModule save(CourseModule courseModule){
        return this.moduleRepository.save(courseModule);
    }

    public List<CourseModule> findAll(){
        return this.moduleRepository.findAll();
    }

    public CourseModule update(long id, CourseModuleRequest courseModuleRequest){
        CourseModule module = this.moduleRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Course Module with given Id cannot be found."));

        Course course = this.courseRepository.findById(courseModuleRequest.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course with given Id cannot be found."));

        module.setName(courseModuleRequest.getName());
        module.setDescription(courseModuleRequest.getDescription());
        module.setCourse(course);
        return this.moduleRepository.save(module);
    }
}
