package org.kst.lms.mappers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.RegistrationDTO;
import org.kst.lms.models.Course;
import org.kst.lms.models.Registration;
import org.kst.lms.services.CourseService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class RegistrationMapper {
    private final CourseService courseService;

    public Registration toEntity(RegistrationDTO dto) {
        Registration registration = new Registration();
        registration.setUsername(dto.getUsername());
        registration.setEmail(dto.getEmail());
        registration.setPhoneNumber(dto.getPhoneNumber());

        Set<Course> courses = mapCourseIdsToCourses(dto.getCourseIds());
        registration.setCourses(courses);
        return registration;
    }

    public RegistrationDTO toDTO(Registration registration) {
        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setUsername(registration.getUsername());
        registrationDTO.setEmail(registration.getEmail());
        registrationDTO.setPhoneNumber(registration.getPhoneNumber());

        Set<Long> courseIds = mapCoursesToCourseIds(registration.getCourses());
        registrationDTO.setCourseIds(courseIds);

        return registrationDTO;
    }

    public Set<Course> mapCourseIdsToCourses(Set<Long> courseIds) {
        return new HashSet<>(this.courseService.findByIds(courseIds));
    }

    public Set<Long> mapCoursesToCourseIds(Set<Course> courses) {
        return courses.stream()
                .map(Course::getId)
                .collect(Collectors.toSet());
    }
}
