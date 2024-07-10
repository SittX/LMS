package org.kst.lms.mappers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.CourseClassRequest;
import org.kst.lms.models.Course;
import org.kst.lms.models.CourseClass;
import org.kst.lms.repositories.CourseRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseClassMapper {
    private final CourseRepository courseRepository;

    public CourseClass toEntity(CourseClassRequest request) {
        if (request == null) {
            return null;
        }

        CourseClass courseClass = new CourseClass();
        courseClass.setName(request.getName());
        courseClass.setDescription(request.getDescription());

        // Fetch the Course entity based on the courseId
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course with Id cannot be found"));
        courseClass.setCourse(course);

        return courseClass;
    }
}
