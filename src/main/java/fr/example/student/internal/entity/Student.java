package fr.example.student.internal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "students.student")
@Table(name = "student", schema = "students")
public class Student {

    @Id
    @UuidGenerator
    private UUID id;

    private UUID classroomId;
    
    private UUID establishmentId;

    private String firstname;

    private String lastname;

    private Date birthdate;

    @OneToMany(mappedBy = "student")
    private Set<Absence> absences;

    @OneToMany(mappedBy = "student")
    private Set<Rate> rates;
}
