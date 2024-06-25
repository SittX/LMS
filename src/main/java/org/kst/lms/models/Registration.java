package org.kst.lms.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registrations")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private RegistrationStatus status;

    @ManyToMany
    @JoinTable(
            name = "registration_courses",
            joinColumns = @JoinColumn(name = "registration_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id")
    )
    private List<Course> courses;

    @CreationTimestamp
    private LocalDateTime registrationDate;
}
