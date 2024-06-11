package fr.example.establishment.internal.entity;

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
@Entity
@Table(name = "establishment", schema = "establishments")
public class Establishment {

    @Id
    @UuidGenerator
    private UUID id;

    private String name;

    private String address;

    private String phoneNumber;

    private String email;

    private Integer nbMaxClassroom;

    @OneToMany(mappedBy = "establishment")
    private Set<Employee> employees;

    @OneToMany(mappedBy = "establishment")
    private Set<Activity> activities;
}
