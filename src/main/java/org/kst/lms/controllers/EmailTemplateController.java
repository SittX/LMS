package org.kst.lms.controllers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.models.EmailTemplate;
import org.kst.lms.services.MailTemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/email_template")
@RequiredArgsConstructor
public class EmailTemplateController {
    private final MailTemplateService mailTemplateService;

    @PostMapping
    public ResponseEntity<EmailTemplate> saveNewEmailTemplate(@RequestBody EmailTemplate emailTemplate) {
        return new ResponseEntity<>(this.mailTemplateService.save(emailTemplate), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmailTemplate>> listEmailTemplates() {
        return ResponseEntity.ok(this.mailTemplateService.findAll());
    }
}
