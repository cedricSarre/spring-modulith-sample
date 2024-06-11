package fr.example.student.internal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "students.rate")
@Table(name = "rate", schema = "students")
public class Rate {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    private UUID classroomId;

    private String subject;

    private Float value;

    private Date date;
}
