package fr.example.core.exception;

public record ErrorResponse(String message, Object[] messageArgs) {
}