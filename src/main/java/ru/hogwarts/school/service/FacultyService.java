package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

public interface FacultyService {

  Faculty addFaculty(Faculty faculty);

  Faculty findFaculty(long id);

  Faculty editFaculty(Faculty faculty);

  void deleteFaculty(long id);
}
