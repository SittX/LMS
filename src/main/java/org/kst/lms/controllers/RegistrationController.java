package org.kst.lms.controllers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.models.Registration;
import org.kst.lms.models.enums.RegistrationStatus;
import org.kst.lms.services.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: Decide whether to return RegistrationDto which only include course id list and not whole course objects.
@RestController
@RequestMapping("/api/v1/registrations")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

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
        logger.info("Processing all registrations list");
        var registrations = this.registrationService.findAll();
        logger.info("Total registrations : {}", registrations.size());
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Registration>> getRegistrationByStatus(@RequestParam(name = "status", defaultValue = "registered") final String status) {
        var filteredRegistration = this.registrationService.findAllByStatus(status);
        logger.info("Registrations filtered : {} for status {}", filteredRegistration.size(), status);
        return ResponseEntity.ok(filteredRegistration);
    }

    @PostMapping("/update")
    public ResponseEntity<Registration> updateRegistrationStatus(@RequestParam(name = "reg_id") final long registrationId, @RequestParam(name = "status") final String status) {
        Registration updatedRegistration = this.registrationService.updateRegistrationStatus(registrationId, RegistrationStatus.findByValue(status));
        logger.info("Finished updating registration : {}", updatedRegistration);
        return ResponseEntity.ok(updatedRegistration);
    }

    @PutMapping("/{registrationId}")
    public ResponseEntity<Registration> updateRegistrationDetails( @PathVariable final Long registrationId,@RequestBody final Registration registration) {
        logger.info("Updating registration with Id : {} ", registrationId);
        Registration updatedRegistration = this.registrationService.update(registrationId, registration);
        return ResponseEntity.ok(updatedRegistration);
    }
}
