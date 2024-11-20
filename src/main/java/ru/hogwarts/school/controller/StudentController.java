package ru.hogwarts.school.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
  private final StudentServiceImpl studentServiceImpl;

  @Autowired
  public StudentController(StudentServiceImpl studentServiceImpl) {
    this.studentServiceImpl = studentServiceImpl;
  }

  @GetMapping("{id}")
  public Student getStudentById(@PathVariable Long id) {
    return studentServiceImpl.findStudent(id).orElse(null);
  }

  @GetMapping
  public Collection<Student> findStudentsByAge(@RequestParam int minAge, @RequestParam int maxAge) {
    return studentServiceImpl.findByAgeBetween(minAge, maxAge);
  }

  @PostMapping
  public Student createStudent(@RequestBody Student student) {
    return studentServiceImpl.addStudent(student);
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
  @GetMapping("{id}/faculty")
  public Faculty getFacultyOfStudent(@PathVariable Long id) {
    return (Faculty) studentServiceImpl.getFacultyOfStudent(id);
  }

}