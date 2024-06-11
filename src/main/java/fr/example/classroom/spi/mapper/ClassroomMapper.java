package fr.example.classroom.spi.mapper;

import fr.example.classroom.internal.entity.Classroom;
import fr.example.classroom.spi.dto.ClassroomDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {

    @Mapping(target = "plannings", ignore = true)
    Classroom toEntity(ClassroomDTO classroomDTO);

    ClassroomDTO toDto(Classroom classroom);

    List<ClassroomDTO> toDtos(List<Classroom> classrooms);
}
