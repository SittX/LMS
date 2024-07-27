package org.kst.lms.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SubjectWithScheduleRequest {
    private long subjectId;
    private List<Integer> daysOfWeek;
}
