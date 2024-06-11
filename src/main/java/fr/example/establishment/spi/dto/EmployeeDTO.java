package fr.example.establishment.spi.dto;

import fr.example.core.enumeration.ValueOfEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record EmployeeDTO(
        UUID id,
        @NotBlank(message = "Le champ 'firstname' est obligatoire")
        @Size(max = 255, message = "La taille du champ 'firstname' doit être inférieure ou égale à 255 caractères")
        String firstname,
        @NotBlank(message = "Le champ 'lastname' est obligatoire")
        @Size(max = 255, message = "La taille du champ 'lastname' doit être inférieure ou égale à 255 caractères")
        String lastname,
        @NotBlank(message = "Le champ 'role' est obligatoire")
        @ValueOfEnum(field = "role", enumClass = RoleDTO.class, regexp = "TEACHER|ADMINISTRATION")
        String role,
        @Email
        String email,
        @NotBlank(message = "Le champ 'establishmentId' est obligatoire")
        UUID establishmentId
) {
}
