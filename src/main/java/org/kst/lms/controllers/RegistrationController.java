package org.kst.lms.controllers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.RegistrationDto;
import org.kst.lms.models.Registration;
import org.kst.lms.services.RegistrationService;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registrations")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @GetMapping
    public Page<Registration> getRegistrations(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        return this.registrationService.findAll(page,size);
    }

    @PostMapping
    public ResponseEntity<RegistrationDto> createNewRegistration(@RequestBody RegistrationDto registration) {
        return new ResponseEntity<>(this.registrationService.save(registration), HttpStatus.CREATED);
    }

    @GetMapping("/update")
    public ResponseEntity<String> updateRegistrationStatus(@RequestParam long registrationId, @RequestParam String status) {
      this.registrationService.updateRegistrationStatus(registrationId, status);
      return new ResponseEntity<>(HttpStatus.OK);
    }
}
