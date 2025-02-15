package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Optional;

public interface FacultyService {
    Optional<Faculty> findFaculty(Long id);

    Collection<Faculty> findByColor(String color);

    Collection<Faculty> findByName(String name);

    Collection<Faculty> findAll();

    Faculty addFaculty(Faculty faculty);

    Faculty editFaculty(Faculty faculty);

    void deleteFaculty(long id);

    Collection<Student> getStudentsOfFaculty(Long id);

    boolean existsById(Long id);

}
