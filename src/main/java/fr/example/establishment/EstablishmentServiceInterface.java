package fr.example.establishment;

import fr.example.establishment.internal.entity.Activity;
import fr.example.establishment.internal.entity.Employee;
import fr.example.establishment.internal.entity.Establishment;

import java.util.List;
import java.util.UUID;

public interface EstablishmentServiceInterface {

    Establishment addEstablishment(Establishment establishment);

    void deleteEstablishment(UUID establishmentId);

    int getMaxNumberOfClassroomByEstablishmentId(UUID establishmentId);

    Employee addEmployee(UUID establishmentId, Employee employee);

    Establishment findEstablishmentById(UUID establishmentId);

    Employee findEmployeeByEstablishmentIdAndEmployeeId(UUID establishmentId, UUID employeeId);

    List<Employee> findAllEmployeesByEstablishmentId(UUID establishmentId);

    Activity addActivity(UUID establishmentId, Activity entity);

    void deleteEmployee(UUID establishmentId, UUID employeeId);

    void deleteActivity(UUID establishmentId, UUID activityId);

    List<Activity> findAllActivitiesByEstablishmentId(UUID establishmentId);

    Activity findActivityByEstablishmentIdAndActivityId(UUID establishmentId, UUID activityId);

}
