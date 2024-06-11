package fr.example.establishment.spi.event;

import org.jmolecules.event.annotation.DomainEvent;
import org.springframework.modulith.NamedInterface;

import java.util.UUID;

@NamedInterface("establishment-event-spi")
@DomainEvent
public record EstablishmentDeletedEvent(UUID establishmentId) {
}
