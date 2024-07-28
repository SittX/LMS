package org.kst.lms.services;

import lombok.RequiredArgsConstructor;
import org.kst.lms.exceptions.ResourceNotFoundException;
import org.kst.lms.models.Role;
import org.kst.lms.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findById(long id){
        return this.roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found"));
    }
}
