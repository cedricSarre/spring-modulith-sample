package fr.example.classroom.internal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "classrooms.classroom")
@Table(name = "classroom", schema = "classrooms")
public class Classroom {

    @Id
    @UuidGenerator
    private UUID id;

    private String name;

    private String level;

    private int maxStudentsCapacity;

    private UUID establishmentId;

    @OneToMany(mappedBy = "classroom")
    private Set<Planning> plannings;

}
