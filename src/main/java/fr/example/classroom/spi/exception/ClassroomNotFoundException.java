package fr.example.classroom.spi.exception;

import fr.example.core.exception.InternalNotFoundException;

import java.util.UUID;

public class ClassroomNotFoundException extends InternalNotFoundException {
    public ClassroomNotFoundException(UUID id, String type) {
        super(type + " not found: " + id);
    }

}
