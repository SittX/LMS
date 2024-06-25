package org.kst.lms.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomResponseBody {
    private String status;
    private String message;
    private String details;
}
