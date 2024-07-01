package org.kst.lms.controllers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.RegistrationDTO;
import org.kst.lms.mappers.RegistrationMapper;
import org.kst.lms.models.Registration;
import org.kst.lms.models.enums.RegistrationStatus;
import org.kst.lms.services.RegistrationService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registrations")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final RegistrationMapper registrationMapper;

    @GetMapping("/paging")
    public ResponseEntity<Page<Registration>> getRegistrations(@RequestParam(name = "page", defaultValue = "0") final int page,
                                                               @RequestParam(name = "size", defaultValue = "10") final int size,
                                                               @RequestParam(name = "sortBy", defaultValue = "id") final String sortBy,
                                                               @RequestParam(name = "direction", defaultValue = "asc") final String direction) {
        Page<Registration> registrations = this.registrationService.findAll(page, size, sortBy, direction);
        return ResponseEntity.ok(registrations);
    }

    @GetMapping
    public ResponseEntity<List<Registration>> getRegistrationAll(){
        return ResponseEntity.ok(this.registrationService.findAll());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Registration>> getRegistrationByStatus(@RequestParam(name = "status", defaultValue = "registered") final String status){
        return ResponseEntity.ok(this.registrationService.findAllByStatus(status));
    }

    @PostMapping
    public ResponseEntity<RegistrationDTO> createNewRegistration(@RequestBody final RegistrationDTO registration) {
        RegistrationDTO registrationDTO = this.registrationService.saveNewRegistration(registration);
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<RegistrationDTO> updateRegistrationStatus(@RequestParam(name = "registration_id") final long registrationId,
                                                                    @RequestParam(name = "status") final String status) {
        Registration updatedRegistration = this.registrationService.updateRegistrationStatus(registrationId, RegistrationStatus.findByValue(status));
        return ResponseEntity.ok(this.registrationMapper.toDTO(updatedRegistration));
    }

    @PutMapping
    public ResponseEntity<RegistrationDTO> updateRegistrationDetails(@RequestBody final RegistrationDTO registrationDTO,
                                                                     @RequestParam(value = "id") final Long id){
        Registration updatedRegistration = this.registrationService.update(id, registrationDTO);
        return ResponseEntity.ok(this.registrationMapper.toDTO(updatedRegistration));
    }
}
