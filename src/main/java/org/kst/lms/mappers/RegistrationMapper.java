package org.kst.lms.mappers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.RegistrationRequest;
import org.kst.lms.models.Course;
import org.kst.lms.models.Registration;
import org.kst.lms.repositories.CourseRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RegistrationMapper {
    private final CourseRepository courseRepository;

    public Registration mapToRegistration(RegistrationRequest request){
        if (request == null) {
            return null;
        }

        Registration registration = new Registration();
        registration.setName(request.getName());
        registration.setEmail(request.getEmail());
        registration.setContactNumber(request.getContactNumber());
        registration.setGuardianName(request.getGuardianName());
        registration.setGuardianContactNumber(request.getGuardianContactNumber());
        registration.setStatus(request.getStatus());

        // Convert course IDs to Course entities
        Set<Course> courses = convertCourseIdsToCourses(request.getCourseIds());
        registration.setCourses(courses);

        return registration;
    }

    private Set<Course> convertCourseIdsToCourses(List<Long> courseIds) {
        return new HashSet<>(courseRepository.findAllById(courseIds));
    }
}
