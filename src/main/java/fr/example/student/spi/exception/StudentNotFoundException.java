package fr.example.student.spi.exception;

import fr.example.core.exception.InternalNotFoundException;

import java.util.UUID;

public class StudentNotFoundException extends InternalNotFoundException {
    public StudentNotFoundException(UUID id) {
        super("Student not found: " + id);
    }

}
