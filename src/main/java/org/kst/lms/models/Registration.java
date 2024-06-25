package org.kst.lms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;


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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "registration_courses",
            joinColumns = @JoinColumn(name = "registration"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses;

    @CreationTimestamp
    private LocalDateTime registrationDateTime;

    @UpdateTimestamp
    private LocalDateTime updatedDateTime;

    private LocalDateTime approvedDateTime;
}
