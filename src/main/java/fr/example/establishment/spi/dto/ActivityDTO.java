package fr.example.establishment.spi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.Date;
import java.util.UUID;

@Builder(toBuilder = true)
public record ActivityDTO(
        UUID id,
        @NotBlank(message = "Le champ 'title' est obligatoire")
        @Size(max = 255, message = "La taille du champ 'title' doit être inférieure à 255 caractères")
        String title,
        @NotBlank(message = "Le champ 'description' est obligatoire")
        @Size(max = 255, message = "La taille du champ 'description' doit être inférieure à 255 caractères")
        String description,
        Date date,
        @NotBlank(message = "Le champ 'establishmentId' est obligatoire")
        UUID establishmentId
) {
}
