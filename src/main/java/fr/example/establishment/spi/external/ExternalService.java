package fr.example.establishment.spi.external;

import fr.example.establishment.spi.ExternalInterface;
import fr.example.establishment.spi.event.EstablishmentDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service(value = "establishmentExternalService")
@RequiredArgsConstructor
class ExternalService implements ExternalInterface {

    private final ApplicationEventPublisher event;

    @Override
    public void publishDeleteEstablishmentEvent(UUID establishmentId) {
        event.publishEvent(new EstablishmentDeletedEvent(establishmentId));
    }
}
