package fr.example.classroom.spi.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record ClassroomDTO(
        UUID id,
        @NotBlank(message = "Le champ 'classroomName' est obligatoire")
        @Size(max = 255, message = "La taille du champ 'classroomName' doit être inférieure ou égale à 255 caractères")
        String name,
        @NotNull(message = "Le champ 'establishmentId' est obligatoire")
        UUID establishmentId,
        @NotBlank(message = "Le champ 'level' est obligatoire")
        @Size(max = 255, message = "La taille du champ 'level' doit être inférieure ou égale à 255 caractères")
        String level,
        @NotNull(message = "Le champ 'maxStudentsCapacity' est obligatoire")
        @Min(value = 1, message = "La valeur du champ 'maxStudentsCapacity' doit être supérieure ou égale à 1")
        @Max(value = 35, message = "La valeur du champ 'maxStudentsCapacity' doit être inférieure ou égale à 35")
        Integer maxStudentsCapacity
) {
}
