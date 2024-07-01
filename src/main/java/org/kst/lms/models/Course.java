package org.kst.lms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    @JsonIgnore
    private List<Registration> registrations;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    @JsonIgnore
    private List<User> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private List<Module> modules;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private List<Class> classes;
}

