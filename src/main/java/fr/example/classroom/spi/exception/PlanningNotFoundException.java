package fr.example.classroom.spi.exception;

import fr.example.core.exception.InternalNotFoundException;

import java.util.UUID;

public class PlanningNotFoundException extends InternalNotFoundException {
    public PlanningNotFoundException(UUID classroomId, UUID planningId) {
        super("Planning " + planningId + " of classroom " + classroomId + " not found");
    }

}
