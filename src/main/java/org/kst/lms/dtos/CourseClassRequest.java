package org.kst.lms.dtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseClassRequest {
    private String name;
    private String description;
    private long courseId;
}
