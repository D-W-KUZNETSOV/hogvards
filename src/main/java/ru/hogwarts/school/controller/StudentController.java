package ru.hogwarts.school.controller;

import java.util.Collection;
import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentServiceImpl;

@RestController
@RequestMapping("/student")
public class StudentController {

  private final StudentServiceImpl studentServiceImpl;

  public StudentController(StudentServiceImpl studentServiceImpl) {
    this.studentServiceImpl = studentServiceImpl;
  }

  @GetMapping("{id}")
  public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
    Student student = studentServiceImpl.findStudent(id);
    if (student == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(student);
  }

  @PostMapping
  public Student createStudent(@RequestBody Student student) {
    return studentServiceImpl.addStudent(student);
  }

  @PutMapping
  public ResponseEntity<Student> editStudent(@RequestBody Student student) {
    Student foundStudent = studentServiceImpl.editStudent(student);
    if (foundStudent == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    return ResponseEntity.ok(foundStudent);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
    studentServiceImpl.deleteStudent(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<Collection<Student>> findStudents(@RequestParam(required = false) int age) {
    if (age > 0) {
      return ResponseEntity.ok(studentServiceImpl.findByAge(age));
    }
    return ResponseEntity.ok(Collections.emptyList());
  }


}