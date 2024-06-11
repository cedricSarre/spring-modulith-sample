package fr.example.classroom.internal.domain;

import fr.example.classroom.ClassroomServiceInterface;
import fr.example.classroom.internal.entity.Classroom;
import fr.example.classroom.internal.entity.Planning;
import fr.example.classroom.internal.repository.ClassroomRepository;
import fr.example.classroom.internal.repository.PlanningRepository;
import fr.example.classroom.spi.ExternalInterface;
import fr.example.classroom.spi.exception.ClassroomEmptyException;
import fr.example.classroom.spi.exception.ClassroomFullException;
import fr.example.classroom.spi.exception.ClassroomNotFoundException;
import fr.example.classroom.spi.exception.PlanningNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
class ClassroomService implements ClassroomServiceInterface {

    private final ClassroomRepository classroomRepository;
    private final PlanningRepository planningRepository;
    private final ExternalInterface externalInterface;

    @Override
    public Classroom createClassroom(Classroom classroom) {
        var establishmentId = classroom.getEstablishmentId();

        // On vérifie que l'établissement existe
        externalInterface.findEstablishmentById(establishmentId);

        // On calcule le nombre de classes contenues par l'établisssement
        int currentNumberOfClassrooms = classroomRepository.findByEstablishmentId(establishmentId).size();

        // On vérifie que le nombre maximum de classes autorisé pour cet établissement n'a pas déjà été atteint
        int maxNumberOfClassroom = externalInterface.getMaxNumberOfClassroomByEstablishmentId(establishmentId);
        if (currentNumberOfClassrooms >= maxNumberOfClassroom) {
            log.error("Cannot add a new classroom. Total number of classrooms for establishment {} has already been reached",
                    establishmentId);
            throw new ClassroomFullException(maxNumberOfClassroom, establishmentId);
        }
        return classroomRepository.save(classroom);
    }

    @Override
    public Classroom findClassroomById(UUID classroomId) {
        return classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId, "Classroom"));
    }

    @Override
    public int getMaxNumberOfStudentByClassroomId(UUID classroomId) {
        var classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId, "Classroom"));
        return classroom.getMaxStudentsCapacity();
    }

    @Override
    public List<Classroom> findByEstablishmentId(UUID establishmentId) {
        var classroom = classroomRepository.findByEstablishmentId(establishmentId);
        if (classroom.isEmpty()) {
            throw new ClassroomNotFoundException(establishmentId, "Establishment");
        }
        return classroom;
    }

    @Override
    @Transactional
    public void deleteById(UUID classroomId) {
        classroomRepository.deleteById(classroomId);
    }

    @Override
    public void deleteByEstablishmentId(UUID establishmentId) {
        classroomRepository.deleteByEstablishmentId(establishmentId);
    }

    @Override
    public Planning addPlanning(UUID classroomId, Planning planning) {
        var classroom = findClassroomById(classroomId);
        planning.setClassroom(classroom);
        return planningRepository.save(planning);
    }

    @Override
    public Planning findPlanningById(UUID classroomId, UUID planningId) {
        findClassroomById(classroomId);
        return planningRepository.findById(planningId)
                .orElseThrow(() -> new PlanningNotFoundException(classroomId, planningId));

    }

    @Override
    public List<Planning> findAllPlanningsByClassroomId(UUID classroomId) {
        findClassroomById(classroomId);
        var plannings = planningRepository.findAllByClassroomId(classroomId);
        if (plannings.isEmpty()) {
            throw new ClassroomEmptyException(classroomId, "planning");
        }
        return plannings;
    }

    @Override
    public void deletePlanningById(UUID classroomId, UUID planningId) {
        findClassroomById(classroomId);
        planningRepository.deleteById(planningId);
    }


}
