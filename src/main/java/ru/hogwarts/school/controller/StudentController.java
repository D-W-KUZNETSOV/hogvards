package ru.hogwarts.school.controller;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
  public Collection<Student> createStudentById(@PathVariable Long id) {
    return studentServiceImpl.findAll();
  }

  @PostMapping
  public List<Student> createStudent(@RequestBody Student student) {
    return studentServiceImpl.addStudent();
  }

  @PutMapping("{id}")
  public List<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
    student.setId(id);
    return studentServiceImpl.addStudent();
  }

  @DeleteMapping("{id}")
  public void deleteStudent(@PathVariable Long id) {
    studentServiceImpl.deleteStudent(id);
  }
  @GetMapping("/age-between")
  public Collection<Student> getStudentsByAgeBetween(@RequestParam int min, @RequestParam int max) {
    return studentServiceImpl.getStudentsByAgeBetween(min, max);
  }

}