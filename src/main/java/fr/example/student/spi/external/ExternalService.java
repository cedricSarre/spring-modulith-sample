package fr.example.student.spi.external;

import fr.example.classroom.ClassroomServiceInterface;
import fr.example.establishment.EstablishmentServiceInterface;
import fr.example.student.spi.ExternalInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service(value = "studentExternalService")
@RequiredArgsConstructor
class ExternalService implements ExternalInterface {

    private final EstablishmentServiceInterface establishmentServiceInterface;
    private final ClassroomServiceInterface classroomServiceInterface;

    @Override
    public void findEstablishmentById(UUID establishmentId) {
        establishmentServiceInterface.findEstablishmentById(establishmentId);
    }

    @Override
    public void findClassroomById(UUID classroomId) {
        classroomServiceInterface.findClassroomById(classroomId);
    }
}
