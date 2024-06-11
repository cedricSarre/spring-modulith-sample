package fr.example.student.spi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record AbsenceDTO(
        UUID id,
        @NotBlank(message = "Le champ 'studentId' est obligatoire")
        UUID studentId,
        @NotBlank(message = "Le champ 'date' est obligatoire")
        String date
) {
}
