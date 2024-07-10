package org.kst.lms.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.kst.lms.models.enums.RegistrationStatus;

import java.util.List;

@Getter
@Setter
@Builder
public class RegistrationRequest {
    private String name;
    private String email;
    private String contactNumber;
    private String guardianName;
    private String guardianContactNumber;
    private RegistrationStatus status;
    private List<Long> courseIds ;
}
