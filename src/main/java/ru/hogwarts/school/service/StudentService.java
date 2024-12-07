package ru.hogwarts.school.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import ru.hogwarts.school.model.Student;

public interface StudentService {

    Student addStudent(Student student);

    Optional<Student> findStudent(long id);

    Student putStudent(Student student) throws EntityNotFoundException;


    boolean existsById(Long id);

    void deleteStudent(Long id);

    Collection<Student> findAll();

    Collection<Student> getStudentsByAgeBetween(int minAge, int maxAge);

    List<Student> getStudentsByFacultyId(Long Id);
}