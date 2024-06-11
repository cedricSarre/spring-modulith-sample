package fr.example.student.spi.mapper;

import fr.example.student.internal.entity.Student;
import fr.example.student.spi.dto.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "birthdate", source = "studentDTO.birthdate",
            dateFormat = "yyyy-MM-dd")
    @Mapping(target = "absences", ignore = true)
    @Mapping(target = "rates", ignore = true)
    Student toEntity(StudentDTO studentDTO);

    @Mapping(target = "birthdate", source = "student.birthdate",
            dateFormat = "yyyy-MM-dd")
    StudentDTO toDto(Student student);

    @Mapping(target = "birthdate", source = "students.birthdate",
            dateFormat = "yyyy-MM-dd")
    List<StudentDTO> toDtos(List<Student> students);
}
