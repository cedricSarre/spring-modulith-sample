package fr.example.student.spi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record RateDTO(
        UUID id,
        @NotBlank(message = "Le champ 'studentId' est obligatoire")
        UUID studentId,
        @NotBlank(message = "Le subject 'subject' est obligatoire")
        @Size(max = 255, message = "La taille du champ 'subject' doit être inférieure ou égale à 255 caractères")
        String subject,
        @NotBlank(message = "Le champ 'date' est obligatoire")
        String date,
        @NotBlank(message = "Le champ 'Value' est obligatoire")
        @PositiveOrZero(message = "La valeur du champ 'value' doit être supérieure ou égale à 0")
        Float value
) {
}
