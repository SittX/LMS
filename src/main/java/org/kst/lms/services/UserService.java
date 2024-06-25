package org.kst.lms.services;

import lombok.RequiredArgsConstructor;
import org.kst.lms.exceptions.ResourceNotFoundException;
import org.kst.lms.mails.MailService;
import org.kst.lms.models.Course;
import org.kst.lms.models.EmailTemplate;
import org.kst.lms.models.Registration;
import org.kst.lms.models.User;
import org.kst.lms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CourseService courseService;
    private final MailTemplateService mailTemplateService;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.defaultPassword}")
    private String defaultPassword;

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public User mapFromRegistration(Registration registration) {
        User user = new User();
        user.setEmail(registration.getEmail());
        user.setUsername(registration.getUsername());
        user.setPhoneNumber(registration.getPhoneNumber());
        return user;
    }

    public void createCustomerFromRegistration(Registration registration) {
        User user = mapFromRegistration(registration);
        Set<Course> courses = registration
                .getCourses().stream()
                .map(Course::getId)
                .map(courseService::findById)
                .collect(Collectors.toSet());
        user.setCourses(courses);

        user.setPassword(this.passwordEncoder.encode(defaultPassword));
        this.save(user);

        EmailTemplate template = this.mailTemplateService.searchEmailTemplateById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Email Template does not exist."));
        this.mailService.sendEmail(template);
    }

}
