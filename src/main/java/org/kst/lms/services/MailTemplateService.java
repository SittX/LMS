package org.kst.lms.services;

import lombok.RequiredArgsConstructor;
import org.kst.lms.models.EmailTemplate;
import org.kst.lms.repositories.EmailTemplateRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MailTemplateService {
    private final EmailTemplateRepository emailTemplateRepository;

    public EmailTemplate searchEmailTemplateById(long id){
        return this.emailTemplateRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Email template found."));
    }
}
