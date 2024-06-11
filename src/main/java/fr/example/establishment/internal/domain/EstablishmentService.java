package fr.example.establishment.internal.domain;

import fr.example.establishment.EstablishmentServiceInterface;
import fr.example.establishment.internal.entity.Activity;
import fr.example.establishment.internal.entity.Employee;
import fr.example.establishment.internal.entity.Establishment;
import fr.example.establishment.internal.repository.ActivityRepository;
import fr.example.establishment.internal.repository.EmployeeRepository;
import fr.example.establishment.internal.repository.EstablishmentRepository;
import fr.example.establishment.spi.ExternalInterface;
import fr.example.establishment.spi.exception.ActivityNotFoundException;
import fr.example.establishment.spi.exception.EmployeeNotFoundException;
import fr.example.establishment.spi.exception.EstablishmentEmptyException;
import fr.example.establishment.spi.exception.EstablishmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class EstablishmentService implements EstablishmentServiceInterface {

    private final ExternalInterface externalInterface;
    private final EstablishmentRepository establishmentRepository;
    private final EmployeeRepository employeeRepository;
    private final ActivityRepository activityRepository;

    @Override
    public Establishment addEstablishment(Establishment establishment) {
        return establishmentRepository.save(establishment);
    }

    @Override
    @Transactional
    public void deleteEstablishment(UUID establishmentId) {
        establishmentRepository.deleteById(establishmentId);
        externalInterface.publishDeleteEstablishmentEvent(establishmentId);
    }

    @Override
    public int getMaxNumberOfClassroomByEstablishmentId(UUID establishmentId) {
        var establishment = establishmentRepository.findById(establishmentId)
                .orElseThrow(() -> new EstablishmentNotFoundException(establishmentId));
        return establishment.getNbMaxClassroom();
    }

    @Override
    public Establishment findEstablishmentById(UUID establishmentId) {
        return establishmentRepository.findById(establishmentId)
                .orElseThrow(() -> new EstablishmentNotFoundException(establishmentId));
    }

    @Override
    public Employee addEmployee(UUID establishmentId, Employee employee) {
        var establishment = findEstablishmentById(establishmentId);
        employee.setEstablishment(establishment);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findEmployeeByEstablishmentIdAndEmployeeId(UUID establishmentId, UUID employeeId) {
        findEstablishmentById(establishmentId);
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(establishmentId, employeeId));
    }

    @Override
    public List<Employee> findAllEmployeesByEstablishmentId(UUID establishmentId) {
        findEstablishmentById(establishmentId);
        var employees = employeeRepository.findAllByEstablishmentId(establishmentId);
        if (employees.isEmpty()) {
            throw new EstablishmentEmptyException(establishmentId, "employee");
        }
        return employees;
    }

    @Override
    public void deleteEmployee(UUID establishmentId, UUID employeeId) {
        findEstablishmentById(establishmentId);
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public Activity addActivity(UUID establishmentId, Activity activity) {
        var establishment = findEstablishmentById(establishmentId);
        activity.setEstablishment(establishment);
        return activityRepository.save(activity);
    }

    @Override
    public Activity findActivityByEstablishmentIdAndActivityId(UUID establishmentId, UUID activityId) {
        findEstablishmentById(establishmentId);
        return activityRepository.findById(activityId)
                .orElseThrow(() -> new ActivityNotFoundException(establishmentId, activityId));
    }

    @Override
    public List<Activity> findAllActivitiesByEstablishmentId(UUID establishmentId) {
        findEstablishmentById(establishmentId);
        var activities = activityRepository.findAllByEstablishmentId(establishmentId);
        if (activities.isEmpty()) {
            throw new EstablishmentEmptyException(establishmentId, "activity");
        }
        return activities;
    }

    @Override
    public void deleteActivity(UUID establishmentId, UUID activityId) {
        findEstablishmentById(establishmentId);
        activityRepository.deleteById(activityId);
    }


}
