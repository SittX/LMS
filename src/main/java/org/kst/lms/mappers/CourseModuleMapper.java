package org.kst.lms.mappers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.CourseModuleRequest;
import org.kst.lms.models.CourseClass;
import org.kst.lms.models.CourseModule;
import org.kst.lms.repositories.CourseClassRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseModuleMapper {
    private final CourseClassRepository courseClassRepository;

    public CourseModule toEntity(CourseModuleRequest request) {
        if (request == null) {
            return null;
        }

        CourseModule courseModule = new CourseModule();
        courseModule.setName(request.getName());
        courseModule.setDescription(request.getDescription());

        // Fetch the Course entity based on the courseId
        CourseClass courseClass = courseClassRepository.findById(request.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
//        courseModule.setCourseClass(courseClass);

        return courseModule;
    }
}
