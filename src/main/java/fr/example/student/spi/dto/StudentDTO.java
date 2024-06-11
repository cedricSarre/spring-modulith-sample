package fr.example.student.spi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record StudentDTO(
        UUID id,
        @NotBlank(message = "Le champ 'firstname' est obligatoire")
        @Size(max = 255, message = "La taille du champ 'firstname' doit être inférieure ou égale à 255 caractères")
        String firstname,
        @NotBlank(message = "Le champ 'lastname' est obligatoire")
        @Size(max = 255, message = "La taille du champ 'lastname' doit être inférieure ou égale à 255 caractères")
        String lastname,
        @NotBlank(message = "Le champ 'birthdate' est obligatoire")
        String birthdate,
        UUID classroomId,
        UUID establishmentId) {
}
