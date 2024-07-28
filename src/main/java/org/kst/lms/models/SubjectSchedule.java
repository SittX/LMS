package org.kst.lms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.kst.lms.models.enums.DaysOfWeek;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subject_schedules")
public class SubjectSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ElementCollection(fetch = FetchType.LAZY, targetClass = DaysOfWeek.class)
    @CollectionTable(name = "days_of_week")
    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private Set<DaysOfWeek> schedules;

    // TODO : When saving SubjectSchedule, it should already have relation to the course id
    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;
}
