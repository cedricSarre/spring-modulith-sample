package fr.example.student.spi.mapper;

import fr.example.student.internal.entity.Rate;
import fr.example.student.spi.dto.RateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RateMapper {

    @Mapping(target = "date", source = "rateDTO.date",
            dateFormat = "yyyy-MM-dd")
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "classroomId", ignore = true)
    Rate toEntity(RateDTO rateDTO);

    @Mapping(target = "date", source = "rate.date",
            dateFormat = "yyyy-MM-dd")
    @Mapping(target = "studentId", ignore = true)
    RateDTO toDto(Rate rate);

    @Mapping(target = "date", source = "rates.date",
            dateFormat = "yyyy-MM-dd")
    List<RateDTO> toDtos(List<Rate> rate);
}
