package ru.hogwarts.school.service;

import java.util.Collection;
import java.util.Optional;
import ru.hogwarts.school.model.Student;

public interface StudentService {

  Student addStudent(Student student);


  Optional findStudent(long studentId);

  Student editStudent(Student student);

  void deleteStudent(long studentId);

  Collection<Student> findAll();

  Collection<Student> findByAgeBetween(int minAge, int maxAge);
}