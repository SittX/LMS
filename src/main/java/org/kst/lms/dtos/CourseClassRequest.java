package org.kst.lms.dtos;


import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseClassRequest {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long teacherId;
    private Set<SubjectSchedulesRequest> subjectSchedules;
}
