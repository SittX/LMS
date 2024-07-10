package org.kst.lms.controllers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.models.Registration;
import org.kst.lms.models.enums.RegistrationStatus;
import org.kst.lms.services.RegistrationService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registrations")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @GetMapping("/paging")
    public ResponseEntity<Page<Registration>> getRegistrations(@RequestParam(name = "page", defaultValue = "0") final int page,
                                                               @RequestParam(name = "size", defaultValue = "10") final int size,
                                                               @RequestParam(name = "sortBy", defaultValue = "id") final String sortBy,
                                                               @RequestParam(name = "direction", defaultValue = "asc") final String direction) {
        Page<Registration> registrations = this.registrationService.findAll(page, size, sortBy, direction);
        return ResponseEntity.ok(registrations);
    }

    @GetMapping
    public ResponseEntity<List<Registration>> getRegistrationAll() {
        return ResponseEntity.ok(this.registrationService.findAll());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Registration>> getRegistrationByStatus(@RequestParam(name = "status", defaultValue = "registered") final String status) {
        return ResponseEntity.ok(this.registrationService.findAllByStatus(status));
    }

    @PostMapping("/{registrationId}")
    public ResponseEntity<Registration> updateRegistrationStatus(@PathVariable final long registrationId,
                                                                    @RequestParam(name = "status") final String status) {
        Registration updatedRegistration = this.registrationService.updateRegistrationStatus(registrationId, RegistrationStatus.findByValue(status));
        return ResponseEntity.ok(updatedRegistration);
    }

    @PutMapping("/{registrationId}")
    public ResponseEntity<Registration> updateRegistrationDetails(@RequestBody final Registration registration,
                                                                     @PathVariable final Long registrationId) {
        Registration updatedRegistration = this.registrationService.update(registrationId, registration);
        return ResponseEntity.ok(updatedRegistration);
    }
}
