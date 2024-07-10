package org.kst.lms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.kst.lms.dtos.RegistrationRequest;
import org.kst.lms.models.enums.RegistrationStatus;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
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

    @Column(name = "name",nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, length = 80, nullable = false)
    private String contactNumber;

    @Column(unique = true, nullable = false)
    private String guardianName;

    @Column(unique = true, nullable = false)
    private String guardianContactNumber;

    @Enumerated(EnumType.STRING)
    private RegistrationStatus status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "registration_courses",
            joinColumns = @JoinColumn(name = "registration_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses;

    @CreationTimestamp
    private LocalDateTime registrationDateTime;

    @UpdateTimestamp
    private LocalDateTime updatedDateTime;

    private LocalDateTime approvedDateTime;



}
