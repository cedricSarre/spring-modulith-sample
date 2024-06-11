package fr.example.establishment.internal.entity;

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
@Entity(name = "establishments.employee")
@Table(name = "employee", schema = "establishments")
public class Employee {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;

    private String firstname;

    private String lastname;

    private String role;

    private String email;
}
