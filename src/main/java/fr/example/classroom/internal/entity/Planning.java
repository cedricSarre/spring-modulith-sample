package fr.example.classroom.internal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "classrooms.planning")
@Table(name = "planning", schema = "classrooms")
public class Planning {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    private int day;

    private int beginHour;

    private int endHour;

    private String subject;

    private UUID teacherId;

    private String location;
}
