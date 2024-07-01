package org.kst.lms.services;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.RegistrationDTO;
import org.kst.lms.exceptions.ResourceAlreadyProcessedException;
import org.kst.lms.mappers.RegistrationMapper;
import org.kst.lms.models.Registration;
import org.kst.lms.models.enums.RegistrationStatus;
import org.kst.lms.repositories.RegistrationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final UserService userService;
    private final RegistrationMapper registrationMapper;

    public RegistrationDTO saveNewRegistration(RegistrationDTO registrationDto) {
        Registration registration = this.registrationMapper.toEntity(registrationDto);
        registration.setStatus(RegistrationStatus.REGISTERED);
        return this.registrationMapper.toDTO(this.registrationRepository.save(registration));
    }

    public List<Registration> findAll(){
        return this.registrationRepository.findAll();
    }

    public Page<Registration> findAll(int page, int size, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return this.registrationRepository.findAll(pageRequest);
    }

    public List<Registration> findAllByStatus(String status){
        return this.registrationRepository.findAllByStatus(RegistrationStatus.findByValue(status));
    }

    /* Update registration status in the Registration table and insert into User */
    public Registration updateRegistrationStatus(Long registrationId, RegistrationStatus registrationStatus) {
        Registration registration = this.registrationRepository
                .findById(registrationId)
                .orElseThrow(() -> new NoSuchElementException("Registration with the given Id cannot be found."));

        if (registration.getStatus().equals(RegistrationStatus.APPROVED) || registration.getStatus().equals(RegistrationStatus.DENIED)) {
            throw new ResourceAlreadyProcessedException("Registration has been processed.");
        }

        registration.setStatus(registrationStatus);
        registration.setApprovedDateTime(LocalDateTime.now());
        Registration updatedRegistration = registrationRepository.save(registration);

        if(registrationStatus.equals(RegistrationStatus.APPROVED)){
            userService.createCustomerFromRegistration(registration);
        }
        return updatedRegistration;
    }

    public Registration update(final long id, final RegistrationDTO registrationDTO){
        Registration registration = this.registrationMapper.toEntity(registrationDTO);
        registration.setId(id);
       return this.registrationRepository.save(registration);
    }
}
