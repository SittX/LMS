package org.kst.lms.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDTO {
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
