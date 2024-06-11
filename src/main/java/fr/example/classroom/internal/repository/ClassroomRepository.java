package fr.example.classroom.internal.repository;

import fr.example.classroom.internal.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, UUID> {

    void deleteByEstablishmentId(UUID establishmentId);

    List<Classroom> findByEstablishmentId(UUID establishmentId);
}
