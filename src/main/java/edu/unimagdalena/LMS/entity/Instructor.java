package edu.unimagdalena.LMS.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "INSTRUCTORS")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToOne(mappedBy = "instructor")
    private Instructor_profile profile;

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;
}