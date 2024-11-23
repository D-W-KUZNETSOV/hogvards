package ru.hogwarts.school.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

  List<Student> findByAgeBetween(int minAge, int maxAge);
}
