package fr.example.core.exception;

public class InternalNotFoundException extends RuntimeException {
    public InternalNotFoundException(String message) {
        super(message);
    }
}
