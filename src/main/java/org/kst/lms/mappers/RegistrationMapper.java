package org.kst.lms.mappers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.RegistrationRequest;
import org.kst.lms.models.CourseClass;
import org.kst.lms.models.Registration;
import org.kst.lms.repositories.CourseClassRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RegistrationMapper {
    private final CourseClassRepository courseClassRepository;

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
        Set<CourseClass> courseClasses = convertCourseIdsToCourses(request.getCourseIds());
        registration.setCourseClasses(courseClasses);

        return registration;
    }

    private Set<CourseClass> convertCourseIdsToCourses(List<Long> courseIds) {
        return new HashSet<>(courseClassRepository.findAllById(courseIds));
    }
}
