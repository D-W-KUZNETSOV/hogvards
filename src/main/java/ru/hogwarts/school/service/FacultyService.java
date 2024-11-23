package ru.hogwarts.school.service;

import java.util.Collection;
import java.util.Optional;
import ru.hogwarts.school.model.Faculty;

public interface FacultyService {

  Faculty addFaculty(Faculty faculty);

  Optional<Faculty> findFaculty(long id);

  Faculty editFaculty(Faculty faculty);

  void deleteFaculty(long id);

  Collection<Faculty> findByName(String name);

  Collection<Faculty> findByColor(String color);

  Collection<Faculty> findAll();
}
