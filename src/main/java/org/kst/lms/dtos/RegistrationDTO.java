package org.kst.lms.dtos;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    private String username;
    private String email;
    private String phoneNumber;
    private Set<Long> courseIds;
}
