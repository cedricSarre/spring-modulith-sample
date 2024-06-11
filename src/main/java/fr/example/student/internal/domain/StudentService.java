package fr.example.student.internal.domain;

import fr.example.student.StudentServiceInterface;
import fr.example.student.internal.entity.Student;
import fr.example.student.internal.repository.StudentRepository;
import fr.example.student.spi.ExternalInterface;
import fr.example.student.spi.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class StudentService implements StudentServiceInterface {

    private final StudentRepository studentRepository;
    private final ExternalInterface externalInterface;

    @Override
    public Student createStudent(Student student) {
        if (student.getEstablishmentId() != null) {
            externalInterface.findEstablishmentById(student.getClassroomId());
        }
        if (student.getClassroomId() != null) {
            externalInterface.findClassroomById(student.getClassroomId());
        }
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(UUID studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public void unlinkStudentToEstablishment(Student student) {
        student.setEstablishmentId(null);
        studentRepository.save(student);
    }

    @Override
    public List<Student> findAllByEstablishmentId(UUID establishmentId) {
        return studentRepository.findAllByEstablishmentId(establishmentId);
    }

    @Override
    public Student findStudentById(UUID studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
    }

}
