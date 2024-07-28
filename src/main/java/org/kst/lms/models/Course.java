package org.kst.lms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    @JsonIgnore
    private List<Registration> registrations = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private List<SubjectSchedule> subjectSchedules = new ArrayList<>();
}

