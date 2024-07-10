package org.kst.lms.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseModuleRequest {
    private String name;
    private String description;
    private Long courseId;
}
