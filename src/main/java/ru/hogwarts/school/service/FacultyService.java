package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

public interface FacultyService {

  Faculty addFaculty(Faculty faculty);
  Faculty findFaculty(long id);
  Faculty editFaculty(long id,Student student);
  Faculty deleteFaculty(long id);
}
