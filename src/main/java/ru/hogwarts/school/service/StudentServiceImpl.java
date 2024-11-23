package ru.hogwarts.school.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public final class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  public StudentServiceImpl(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public List<Student> addStudent() {
    return List.of();
  }

  @Override
  public Student addStudent(Student student) {
    return studentRepository.save(student);
  }


  @Override
  public Optional findStudent(long Id) {
    return studentRepository.findById(Id);
  }


  public Student editStudent(Student student) {
    return studentRepository.save(student);
  }

  @Override
  public void deleteStudent(long Id) {

  }

  @Override
  public Collection<Student> findAll() {
    return List.of();
  }

  public List<Student> getStudentsByAgeBetween(int min, int max) {
    return studentRepository.findByAgeBetween(min, max);
  }


  public void deleteStudent(Long id) {
    studentRepository.deleteById(id);
  }

  public Faculty getAllFacultiesFromStudents(Long id) {
    Student student = studentRepository.findById(id).orElse(null);
    return student != null ? student.getFaculty() : null;
  }
}