package fr.example.establishment.spi.exception;

import fr.example.core.exception.InternalNotFoundException;

import java.util.UUID;

public class EstablishmentEmptyException extends InternalNotFoundException {

    public EstablishmentEmptyException(UUID establishmentId, String type) {
        super("Establishment " + establishmentId + " does not have " + type);
    }
}
