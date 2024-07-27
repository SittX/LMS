package org.kst.lms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kst.lms.models.enums.AttendanceStatus;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name = "attendances")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate attendanceDate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "class_id", referencedColumnName = "id")
//    private Subject subject;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus attendanceStatus;
}
