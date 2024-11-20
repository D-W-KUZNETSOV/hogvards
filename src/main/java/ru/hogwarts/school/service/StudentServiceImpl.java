package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class StudentServiceImpl {
  private final StudentRepository studentRepository;

  @Autowired
  public StudentServiceImpl(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public Optional<Student> findStudent(Long id) {
    return studentRepository.findById(id);
  }

  public Collection<Student> findByAgeBetween(int minAge, int maxAge) {
    return studentRepository.findByAgeBetween(minAge, maxAge);
  }

  public Student addStudent(Student student) {
    return studentRepository.save(student);
  }

  public Student editStudent(Student student) {
    return studentRepository.save(student);
  }

  public void deleteStudent(Long id) {
    studentRepository.deleteById(id);
  }
  public Object getFacultyOfStudent(Long studentId) {
    return studentRepository.findById(studentId)
        .map(Student::getFaculty)
        .orElse(null);
  }
}