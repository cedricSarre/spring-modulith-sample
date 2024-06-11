package fr.example.student;

import fr.example.student.internal.entity.Student;
import fr.example.student.spi.dto.StudentDTO;
import fr.example.student.spi.mapper.StudentMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentServiceInterface studentServiceInterface;
    private final StudentMapper studentMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO addStudent(@Valid @RequestBody StudentDTO studentDTO) {
        Student response = studentServiceInterface.createStudent(studentMapper.toEntity(studentDTO));
        return studentMapper.toDto(response);
    }

    @GetMapping("/{studentId}")
    public StudentDTO findStudentById(@PathVariable UUID studentId) {
        Student response = studentServiceInterface.findStudentById(studentId);
        return studentMapper.toDto(response);
    }

    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable UUID studentId) {
        studentServiceInterface.deleteStudent(studentId);
    }

}
