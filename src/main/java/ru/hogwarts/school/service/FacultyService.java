package ru.hogwarts.school.service;

import java.util.Optional;

import ru.hogwarts.school.model.Faculty;

public interface FacultyService {

    Optional<Faculty> findFaculty(Long id);

    Faculty addFaculty(Faculty faculty);


    Faculty editFaculty(Faculty faculty);

    void deleteFaculty(long id);
}
