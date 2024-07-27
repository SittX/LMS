package org.kst.lms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.kst.lms.models.enums.DaysOfWeek;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectWithSchedule {
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonIgnore
    private Subject subject;

    @ElementCollection(fetch = FetchType.LAZY, targetClass = DaysOfWeek.class)
    @CollectionTable(name = "days_of_week")
    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private Set<DaysOfWeek> schedules;

    @ManyToOne
    @JoinColumn(name = "course_class_id")
    @JsonIgnore
    private CourseClass courseClass;
}
