package org.kst.lms.dtos;

import lombok.*;
import org.kst.lms.models.Course;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RegistrationDto {
    private String username;
    private String email;
    private String phoneNumber;
    private List<Long> courseId;
}
