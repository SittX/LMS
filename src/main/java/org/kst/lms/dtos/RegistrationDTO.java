package org.kst.lms.dtos;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    private String name;
    private String email;
    private String contactNumber;
    private String guardianName;
    private String guardianContactNumber;
    private Set<Long> courseIds;
}
