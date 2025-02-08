package ru.hogwarts.school.controller;


import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.FacultyServiceImpl;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentServiceImpl studentServiceImpl;




    @Autowired
    private FacultyServiceImpl facultyServiceImpl;
    @Autowired
    private  StudentRepository studentRepository;

    public StudentController(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable Long id) {
        Optional<Student> student = studentServiceImpl.findStudent(id);
        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentServiceImpl.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @PutMapping("{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id);
        return studentServiceImpl.putStudent(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (studentServiceImpl.existsById(id)) {
            studentServiceImpl.deleteStudent(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/age-between")
    public ResponseEntity<List<Student>> getStudentsByAgeBetween(@RequestParam int minAge, @RequestParam int maxAge) {
        List<Student> students = studentRepository.findByAgeBetween(minAge, maxAge);
        System.out.println("Returning students: " + students); // Логирование
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<Student>> getStudentsByFacultyId(@PathVariable Long id) {
        List<Student> students = studentServiceImpl.getStudentsByFacultyId(id);
        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }
}
