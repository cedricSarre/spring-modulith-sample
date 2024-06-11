package fr.example.establishment.internal.entity;

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
@Entity(name = "establishments.activity")
@Table(name = "activity", schema = "establishments")
public class Activity {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;

    private String title;

    private String description;

    private Date date;
}
