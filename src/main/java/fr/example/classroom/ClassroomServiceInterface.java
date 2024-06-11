package fr.example.classroom;

import fr.example.classroom.internal.entity.Classroom;
import fr.example.classroom.internal.entity.Planning;

import java.util.List;
import java.util.UUID;

public interface ClassroomServiceInterface {

    Classroom createClassroom(Classroom classroom);

    void deleteById(UUID classroomId);

    Classroom findClassroomById(UUID classroomId);

    int getMaxNumberOfStudentByClassroomId(UUID classroomId);

    void deleteByEstablishmentId(UUID establishmentId);

    List<Classroom> findByEstablishmentId(UUID establishmentId);

    Planning addPlanning(UUID classroomId, Planning planning);

    Planning findPlanningById(UUID classroomId, UUID planningId);

    List<Planning> findAllPlanningsByClassroomId(UUID classroomId);

    void deletePlanningById(UUID classroomId, UUID planningId);
}
