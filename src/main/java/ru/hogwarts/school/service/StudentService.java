package ru.hogwarts.school.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import ru.hogwarts.school.model.Student;

public interface StudentService {

  List<Student> addStudent();


  Student addStudent(Student student);

  Optional findStudent(long Id);

  Student editStudent(Student student);

  void deleteStudent(long Id);

  Collection<Student> findAll();

  List<Student> getStudentsByAgeBetween(int min, int max);

  void deleteStudent(Long id);
}