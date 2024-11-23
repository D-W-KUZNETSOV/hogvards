package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

  private final FacultyRepository facultyRepository;

  @Autowired
  public FacultyServiceImpl(FacultyRepository facultyRepository) {
    this.facultyRepository = facultyRepository;
  }

  @Override
  public Optional<Faculty> findFaculty(long id) {
    return facultyRepository.findById(id);
  }

  @Override
  public Collection<Faculty> findByColor(String color) {
    return facultyRepository.findByColorIgnoreCase(color);
  }

  @Override
  public Collection<Faculty> findByName(String name) {
    return facultyRepository.findByNameIgnoreCase(name);
  }

  @Override
  public Collection<Faculty> findAll() {
    return facultyRepository.findAll();
  }

  @Override
  public Faculty addFaculty(Faculty faculty) {
    return facultyRepository.save(faculty);
  }

  @Override
  public Faculty editFaculty(Faculty faculty) {
    return facultyRepository.save(faculty);
  }

  @Override
  public void deleteFaculty(long id) {
    if (facultyRepository.existsById(id)) {
      facultyRepository.deleteById(id);
    }
  }

  public Collection<Student> getStudentsOfFaculty(Long facultyId) {
    return facultyRepository.findById(facultyId)
        .map(Faculty::getStudents)
        .orElse(null);
  }
}

