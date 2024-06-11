package fr.example.establishment.spi;

import java.util.UUID;

public interface ExternalInterface {

    void publishDeleteEstablishmentEvent(UUID establishmentId);
}
