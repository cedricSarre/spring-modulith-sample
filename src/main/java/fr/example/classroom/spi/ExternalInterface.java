package fr.example.classroom.spi;

import java.util.UUID;

public interface ExternalInterface {

    void findEstablishmentById(UUID establishmentId);

    int getMaxNumberOfClassroomByEstablishmentId(UUID establishmentId);
}
