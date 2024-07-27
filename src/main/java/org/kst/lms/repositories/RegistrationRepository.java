package org.kst.lms.repositories;

import org.kst.lms.models.Registration;
import org.kst.lms.models.enums.RegistrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    Optional<Registration> findByIdAndStatus(Long id, RegistrationStatus status);
    List<Registration> findAllByStatus(RegistrationStatus status);
}
