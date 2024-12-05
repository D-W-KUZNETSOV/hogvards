package ru.hogwarts.school.controller;


import java.util.Collections;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyServiceImpl;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentServiceImpl studentServiceImpl;


    @Autowired
    private FacultyServiceImpl facultyServiceImpl;

    public StudentController(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

    @GetMapping("{id}")
    public Student findStudentById(@PathVariable Long id) {
        return studentServiceImpl.findStudent(id)
                .orElse(null);
    }

    @PostMapping("/Student")
    public ResponseEntity<Student> addStudent(@RequestBody Student student, @RequestParam Long facultyId) {
        Faculty faculty = facultyServiceImpl.findFaculty(facultyId)
                .orElseThrow(() -> new EntityNotFoundException("Faculty not found with id " + facultyId));
        student.setFaculty(faculty);
        Student savedStudent = studentServiceImpl.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @PutMapping("{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id);
        return studentServiceImpl.editStudent(student);
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentServiceImpl.deleteStudent(id);
    }

    @GetMapping("/age-between")
    public Collection<Student> getStudentsByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return studentServiceImpl.getStudentsByAgeBetween(min, max);
    }

    @GetMapping("/{id}/Faculty")
    public ResponseEntity<List<Student>> getStudentsByFaculty(@PathVariable Long id) {
        List<Student> students = Collections.unmodifiableList(studentServiceImpl.getStudentsByFacultyId(id));

        if (students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }

        return ResponseEntity.ok(students);
    }
}
