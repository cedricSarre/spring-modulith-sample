package fr.example.establishment.spi.exception;

import fr.example.core.exception.InternalNotFoundException;

import java.util.UUID;

public class EstablishmentNotFoundException extends InternalNotFoundException {
    public EstablishmentNotFoundException(UUID establishmentId) {
        super("Establishment not found: " + establishmentId);
    }

}
