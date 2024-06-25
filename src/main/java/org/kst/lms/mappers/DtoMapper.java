package org.kst.lms.mappers;

import org.kst.lms.dtos.RegistrationDto;
import org.kst.lms.models.Registration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DtoMapper {
    Registration mapToRegistration(RegistrationDto registration);
    RegistrationDto mapToRegistrationRequest(Registration registration);
}
