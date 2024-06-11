package fr.example.classroom;

import fr.example.classroom.internal.entity.Classroom;
import fr.example.classroom.internal.entity.Planning;
import fr.example.classroom.spi.dto.ClassroomDTO;
import fr.example.classroom.spi.dto.PlanningDTO;
import fr.example.classroom.spi.mapper.ClassroomMapper;
import fr.example.classroom.spi.mapper.PlanningMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/classrooms")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomServiceInterface classroomServiceInterface;
    private final ClassroomMapper classroomMapper;
    private final PlanningMapper planningMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClassroomDTO createClassroom(@Valid @RequestBody ClassroomDTO classroomDTO) {
        Classroom response = classroomServiceInterface.createClassroom(classroomMapper.toEntity(classroomDTO));
        return classroomMapper.toDto(response);
    }

    @GetMapping("/{classroomId}")
    public ClassroomDTO findById(@PathVariable UUID classroomId) {
        Classroom response = classroomServiceInterface.findClassroomById(classroomId);
        return classroomMapper.toDto(response);
    }

    @GetMapping
    public List<ClassroomDTO> findByEstablishmentId(@RequestParam UUID establishmentId) {
        List<Classroom> response = classroomServiceInterface.findByEstablishmentId(establishmentId);
        return classroomMapper.toDtos(response);
    }

    @DeleteMapping("/{classroomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClassroom(@PathVariable UUID classroomId) {
        classroomServiceInterface.deleteById(classroomId);
    }

    @PostMapping("/{classroomId}/plannings")
    @ResponseStatus(HttpStatus.CREATED)
    public PlanningDTO addPlanning(@PathVariable UUID classroomId, @Valid @RequestBody PlanningDTO planningDTO) {
        Planning response = classroomServiceInterface.addPlanning(classroomId, planningMapper.toEntity(planningDTO));
        return planningMapper.toDto(response);
    }

    @GetMapping("/{classroomId}/plannings/{planningId}")
    public PlanningDTO findEmployeeById(@PathVariable UUID classroomId, @PathVariable UUID planningId) {
        Planning response = classroomServiceInterface.findPlanningById(classroomId, planningId);
        return planningMapper.toDto(response);
    }

    @GetMapping("/{classroomId}/plannings")
    public List<PlanningDTO> findAllEmployeeByEstablishmentId(@PathVariable UUID classroomId) {
        List<Planning> response = classroomServiceInterface.findAllPlanningsByClassroomId(classroomId);
        return planningMapper.toDtos(response);
    }

    @DeleteMapping("/{classroomId}/plannings/{planningId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable UUID classroomId, @PathVariable UUID planningId) {
        classroomServiceInterface.deletePlanningById(classroomId, planningId);
    }

}
