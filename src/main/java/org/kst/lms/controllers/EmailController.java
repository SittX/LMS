package org.kst.lms.controllers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.mails.MailService;
import org.kst.lms.models.EmailTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
public class EmailController {
    private final MailService mailService;

    @GetMapping
    public String sendEmail() {
        EmailTemplate template = new EmailTemplate();
        template.setSubject("Test Email Subject");
        template.setBody("Test Email Body");
        template.setTemplate("Test Email Template");

        mailService.sendEmail(template);
        return "Mail Send";
    }
}
