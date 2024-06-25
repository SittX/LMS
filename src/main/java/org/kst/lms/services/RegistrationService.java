package org.kst.lms.services;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.RegistrationDto;
import org.kst.lms.mails.MailService;
import org.kst.lms.mappers.DtoMapper;
import org.kst.lms.models.*;
import org.kst.lms.repositories.RegistrationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final UserService userService;
    private final CourseService courseService;
    private final DtoMapper dtoMapper;
    private final MailService mailService;
    private final MailTemplateService mailTemplateService;

    public RegistrationDto save(RegistrationDto registration) {
        Registration newRegistration =  this.registrationRepository.save(dtoMapper.mapToRegistration(registration));
        return this.dtoMapper.mapToRegistrationRequest(newRegistration);
    }

    public Page<Registration> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return this.registrationRepository.findAll(pageRequest);
    }

    public void updateRegistrationStatus(Long registrationId, String status) {
        Registration registration = this.registrationRepository.findByIdAndStatus(registrationId, RegistrationStatus.valueOf(status))
                .orElseThrow(() -> new NoSuchElementException("Registration not found"));

        User user = new User(registration);
        List<Course>  courses = this.courseService.findByRegistrationId(registrationId);
        user.setCourses(courses);

        this.userService.save(user);
        EmailTemplate template = this.mailTemplateService.searchEmailTemplateById(1);
        this.mailService.sendEmail(template);
    }
}
