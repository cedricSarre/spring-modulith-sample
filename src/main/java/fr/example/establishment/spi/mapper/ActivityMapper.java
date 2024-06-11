package fr.example.establishment.spi.mapper;

import fr.example.establishment.internal.entity.Activity;
import fr.example.establishment.spi.dto.ActivityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    @Mapping(target = "establishment", ignore = true)
    Activity toEntity(ActivityDTO activityDTO);

    @Mapping(target = "establishmentId", ignore = true)
    ActivityDTO toDto(Activity activity);

    List<ActivityDTO> toDtos(List<Activity> activities);
}
