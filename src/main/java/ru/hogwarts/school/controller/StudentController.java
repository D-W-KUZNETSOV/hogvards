package ru.hogwarts.school.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentServiceImpl;
import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

  private final StudentServiceImpl studentServiceImpl;


  @Autowired
  private StudentRepository studentRepository;

  public StudentController(StudentServiceImpl studentServiceImpl) {
    this.studentServiceImpl = studentServiceImpl;
  }

  @GetMapping("{id}")
  public List<Student> createStudentById(@PathVariable Long id) {
    return studentServiceImpl.addStudent();
  }


  @PostMapping
  public ResponseEntity<Student> createStudent(@RequestBody Student student) {
    Student createdStudent = studentServiceImpl.addStudent(student);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
  }

  @PutMapping("{id}")
  public Optional<Student> findStudent(@PathVariable Long id, @RequestBody Student student) {
    student.setId(id);
    return studentServiceImpl.findStudent(id);
  }

  @DeleteMapping("{id}")
  public void deleteStudent(@PathVariable Long id) {
    studentServiceImpl.deleteStudent(id);
  }

  @GetMapping("/age-between")
  public Collection<Student> getStudentsByAgeBetween(@RequestParam int min, @RequestParam int max) {
    return studentServiceImpl.getStudentsByAgeBetween(min, max);
  }

  @GetMapping("/{studentId}/faculty")
  public String getFacultyByStudentId(@PathVariable Long studentId) {
    return studentRepository.findById(studentId)
        .map(Student::getFaculty)
        .orElse(null);
  }
}