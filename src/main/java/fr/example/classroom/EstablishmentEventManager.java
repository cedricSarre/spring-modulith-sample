package fr.example.classroom;

import fr.example.establishment.spi.event.EstablishmentDeletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service(value = "ClassroomEstablishmentEventManager")
@RequiredArgsConstructor
@Slf4j
class EstablishmentEventManager {

    private final ClassroomServiceInterface classroomServiceInterface;

    @ApplicationModuleListener
    void onRemovedEstablishmentEvent(EstablishmentDeletedEvent event) {
        var establishmentId = event.establishmentId();
        log.info("Received removing all classrooms of establishment {}.", establishmentId);
        classroomServiceInterface.deleteByEstablishmentId(establishmentId);
        log.info("Finished removing all classrooms of establishment {}.", establishmentId);
    }
}
