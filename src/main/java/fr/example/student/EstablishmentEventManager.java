package fr.example.student;

import fr.example.establishment.spi.event.EstablishmentDeletedEvent;
import fr.example.student.spi.dto.StudentDTO;
import fr.example.student.spi.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "StudentEstablishmentEventManager")
@RequiredArgsConstructor
@Slf4j
class EstablishmentEventManager {

    private final StudentServiceInterface studentServiceInterface;
    private final StudentMapper studentMapper;

    @ApplicationModuleListener
    void onRemovedEstablishmentEvent(EstablishmentDeletedEvent event) {

        var establishmentId = event.establishmentId();

        log.info("Received removal of establishment {}. We need to update the linked students", establishmentId);

        List<StudentDTO> studentDTOS =
                studentMapper.toDtos(studentServiceInterface.findAllByEstablishmentId(establishmentId));

        studentDTOS.forEach(studentDTO -> studentServiceInterface.unlinkStudentToEstablishment(studentMapper.toEntity(studentDTO)));

        log.info("Finished unlinking all students of establishment {}.", establishmentId);
    }
}
