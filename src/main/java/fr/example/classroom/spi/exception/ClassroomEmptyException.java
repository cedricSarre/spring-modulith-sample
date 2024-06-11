package fr.example.classroom.spi.exception;

import fr.example.core.exception.InternalNotFoundException;

import java.util.UUID;

public class ClassroomEmptyException extends InternalNotFoundException {

    public ClassroomEmptyException(UUID classroomId, String type) {
        super("Classroom " + classroomId + " does not have " + type);
    }
}
