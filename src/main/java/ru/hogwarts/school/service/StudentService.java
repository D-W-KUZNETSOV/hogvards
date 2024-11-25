package ru.hogwarts.school.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import ru.hogwarts.school.model.Student;

public interface StudentService {

  Student addStudent(Student student);

  Optional<Student> findStudent(long id);

  Student editStudent(Student student);

  void deleteStudent(long id);

  Collection<Student> findAll();

  List<Student> getStudentsByAgeBetween(int min, int max);
}