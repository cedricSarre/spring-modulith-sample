package fr.example.establishment.spi.exception;

import fr.example.core.exception.InternalNotFoundException;

import java.util.UUID;

public class ActivityNotFoundException extends InternalNotFoundException {
    public ActivityNotFoundException(UUID establishmentId, UUID activityId) {
        super("Activity " + activityId + " of establishment " + establishmentId + " not found");
    }

}
