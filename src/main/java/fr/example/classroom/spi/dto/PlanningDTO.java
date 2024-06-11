package fr.example.classroom.spi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record PlanningDTO(
        UUID id,
        @NotBlank(message = "Le champ 'day' est obligatoire")
        @Size(max = 10, message = "La taille du champ 'day' doit être inférieure ou égale à 10 caractères")
        String day,
        @NotNull(message = "Le champ 'beginHour' est obligatoire")
        @Size(max = 5, message = "La taille du champ 'beginHour' doit être inférieure ou égale à 5 caractères")
        String beginHour,
        @NotBlank(message = "Le champ 'endHour' est obligatoire")
        @Size(max = 5, message = "La taille du champ 'endHour' doit être inférieure ou égale à 5 caractères")
        String endHour,
        @NotBlank(message = "Le champ 'subject' est obligatoire")
        @Size(max = 255, message = "La taille du champ 'subject' doit être inférieure ou égale à 255 caractères")
        String subject,
        @NotNull(message = "Le champ 'teacherId' est obligatoire")
        UUID teacherId,
        @NotBlank(message = "Le champ 'location' est obligatoire")
        @Size(max = 255, message = "La taille du champ 'location' doit être inférieure ou égale à 255 caractères")
        String location,
        @NotNull(message = "Le champ 'classroomId' est obligatoire")
        UUID classroomId
) {
}
