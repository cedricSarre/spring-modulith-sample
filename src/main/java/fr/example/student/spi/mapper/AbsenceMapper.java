package fr.example.student.spi.mapper;

import fr.example.student.internal.entity.Absence;
import fr.example.student.spi.dto.AbsenceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AbsenceMapper {

    @Mapping(target = "date", source = "absenceDTO.date",
            dateFormat = "yyyy-MM-dd")
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "classroomId", ignore = true)
    Absence toEntity(AbsenceDTO absenceDTO);

    @Mapping(target = "date", source = "absence.date",
            dateFormat = "yyyy-MM-dd")
    @Mapping(target = "studentId", ignore = true)
    AbsenceDTO toDto(Absence absence);

    @Mapping(target = "date", source = "absences.date",
            dateFormat = "yyyy-MM-dd")
    List<AbsenceDTO> toDtos(List<Absence> absences);
}
