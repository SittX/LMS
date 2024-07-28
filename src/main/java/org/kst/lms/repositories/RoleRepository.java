package org.kst.lms.repositories;

import lombok.RequiredArgsConstructor;
import org.kst.lms.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
