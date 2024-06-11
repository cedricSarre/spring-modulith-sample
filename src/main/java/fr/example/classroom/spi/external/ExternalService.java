package fr.example.classroom.spi.external;

import fr.example.classroom.spi.ExternalInterface;
import fr.example.establishment.EstablishmentServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service(value = "classroomExternalService")
@RequiredArgsConstructor
@Slf4j
class ExternalService implements ExternalInterface {

    private final EstablishmentServiceInterface establishmentServiceInterface;

    @Override
    public void findEstablishmentById(UUID establishmentId) {
        establishmentServiceInterface.findEstablishmentById(establishmentId);
    }

    @Override
    public int getMaxNumberOfClassroomByEstablishmentId(UUID establishmentId) {
        return establishmentServiceInterface.getMaxNumberOfClassroomByEstablishmentId(establishmentId);
    }

}
