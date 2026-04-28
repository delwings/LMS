package edu.unimagdalena.LMS.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "INSTRUCTOR_PROFILES")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Instructor_profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Column(name = "phone")
    private String phone;

    @Column(name = "bio")
    private String bio;
}