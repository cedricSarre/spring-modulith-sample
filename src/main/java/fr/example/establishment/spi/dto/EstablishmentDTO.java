package fr.example.establishment.spi.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record EstablishmentDTO(
        UUID id,
        @NotBlank(message = "Le champ 'nom' est obligatoire")
        @Size(max = 255, message = "La taille du champ 'nom' doit être inférieure ou égale à 255 caractères")
        String name,
        @Size(max = 255, message = "La taille du champ 'address' doit être inférieure ou égale à 255 caractères")
        String address,
        @Size(max = 15, message = "La taille du champ 'phoneNumber' doit être inférieure ou égale à 15 caractères")
        String phoneNumber,
        @Email
        @Size(max = 255, message = "La taille du champ 'email' doit être inférieure ou égale à 255 caractères")
        String email,
        @NotNull(message = "Le champ 'nbMaxClassroom' est obligatoire")
        @Min(value = 1, message = "La valeur du champ 'nbMaxClassroom' doit être supérieure ou égale à 1")
        @Max(value = 99, message = "La valeur du champ 'nbMaxClassroom' doit être inférieure ou égale à 99")
        Integer nbMaxClassroom
) {
}
