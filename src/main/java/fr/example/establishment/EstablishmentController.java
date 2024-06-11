package fr.example.establishment;

import fr.example.establishment.internal.entity.Employee;
import fr.example.establishment.internal.entity.Establishment;
import fr.example.establishment.spi.dto.ActivityDTO;
import fr.example.establishment.spi.dto.EmployeeDTO;
import fr.example.establishment.spi.dto.EstablishmentDTO;
import fr.example.establishment.spi.mapper.ActivityMapper;
import fr.example.establishment.spi.mapper.EmployeeMapper;
import fr.example.establishment.spi.mapper.EstablishmentMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/establishments")
@RequiredArgsConstructor
public class EstablishmentController {

    private final EstablishmentServiceInterface establishmentServiceInterface;
    private final EstablishmentMapper establishmentMapper;
    private final EmployeeMapper employeeMapper;
    private final ActivityMapper activityMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstablishmentDTO addEstablishment(@Valid @RequestBody EstablishmentDTO establishmentDTO) {
        Establishment response = establishmentServiceInterface.addEstablishment(establishmentMapper.toEntity(establishmentDTO));
        return establishmentMapper.toDto(response);
    }

    @GetMapping("/{establishmentId}")
    public EstablishmentDTO findEstablishmentById(@PathVariable UUID establishmentId) {
        Establishment response = establishmentServiceInterface.findEstablishmentById(establishmentId);
        return establishmentMapper.toDto(response);
    }

    @DeleteMapping("/{establishmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEstablishment(@PathVariable UUID establishmentId) {
        establishmentServiceInterface.deleteEstablishment(establishmentId);
    }

    @PostMapping("/{establishmentId}/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO addEmployee(@PathVariable UUID establishmentId, @Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee response = establishmentServiceInterface.addEmployee(establishmentId, employeeMapper.toEntity(employeeDTO));
        return employeeMapper.toDto(response);
    }

    @GetMapping("/{establishmentId}/employees/{employeeId}")
    public EmployeeDTO findEmployeeById(@PathVariable UUID establishmentId, @PathVariable UUID employeeId) {
        Employee response = establishmentServiceInterface.findEmployeeByEstablishmentIdAndEmployeeId(establishmentId, employeeId);
        return employeeMapper.toDto(response);
    }

    @GetMapping("/{establishmentId}/employees")
    public List<EmployeeDTO> findAllEmployeeByEstablishmentId(@PathVariable UUID establishmentId) {
        List<Employee> response = establishmentServiceInterface.findAllEmployeesByEstablishmentId(establishmentId);
        return employeeMapper.toDtos(response);
    }

    @DeleteMapping("/{establishmentId}/employees/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable UUID establishmentId, @PathVariable UUID employeeId) {
        establishmentServiceInterface.deleteEmployee(establishmentId, employeeId);
    }

    @PostMapping("/{establishmentId}/activities")
    @ResponseStatus(HttpStatus.CREATED)
    public ActivityDTO addActivity(@PathVariable UUID establishmentId, @Valid @RequestBody ActivityDTO activityDTO) {
        var response = establishmentServiceInterface.addActivity(establishmentId, activityMapper.toEntity(activityDTO));
        return activityMapper.toDto(response);
    }

    @GetMapping("/{establishmentId}/activities/{activityId}")
    public ActivityDTO findActivityById(@PathVariable UUID establishmentId, @PathVariable UUID activityId) {
        var response = establishmentServiceInterface.findActivityByEstablishmentIdAndActivityId(establishmentId, activityId);
        return activityMapper.toDto(response);
    }

    @GetMapping("/{establishmentId}/activities")
    public List<ActivityDTO> findAllActivitiesByEstablishmentId(@PathVariable UUID establishmentId) {
        var response = establishmentServiceInterface.findAllActivitiesByEstablishmentId(establishmentId);
        return activityMapper.toDtos(response);
    }

    @DeleteMapping("/{establishmentId}/activities/{activityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAllActivityById(@PathVariable UUID establishmentId, @PathVariable UUID activityId) {
        establishmentServiceInterface.deleteActivity(establishmentId, activityId);
    }
}
