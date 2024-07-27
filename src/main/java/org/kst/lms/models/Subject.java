package org.kst.lms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kst.lms.models.enums.DaysOfWeek;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    @ElementCollection(fetch = FetchType.LAZY, targetClass = DaysOfWeek.class)
//    @CollectionTable(name = "days_of_week")
//    @Column(name = "class_schedule_days")
//    @Enumerated(EnumType.STRING)
//    private Set<DaysOfWeek> schedules;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "class_subjects",
//            joinColumns = @JoinColumn(name = "subject_id"),
//            inverseJoinColumns = @JoinColumn(name = "class_id"))
//    @JsonIgnore
//    private List<CourseClass> classes;

//    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
//    private List<Attendance> attendances;
//
//    @ManyToMany(mappedBy = "subjects", fetch = FetchType.LAZY)
//    private List<User> users;
}
