package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class FacultyServiceImpl {
  private final FacultyRepository facultyRepository;

  @Autowired
  public FacultyServiceImpl(FacultyRepository facultyRepository) {
    this.facultyRepository = facultyRepository;
  }

  public Optional<Faculty> findFaculty(Long id) {
    return facultyRepository.findById(id);
  }

  public Collection<Faculty> findByColor(String color) {
    return facultyRepository.findByColorIgnoreCase(color);
  }

  public Collection<Faculty> findByName(String name) {
    return facultyRepository.findByNameIgnoreCase(name);
  }

  public Collection<Faculty> findAll() {
    return facultyRepository.findAll();
  }

  public Faculty addFaculty(Faculty faculty) {
    return facultyRepository.save(faculty);
  }

  public Faculty editFaculty(Faculty faculty) {
    return facultyRepository.save(faculty);
  }

  public boolean deleteFaculty(Long id) {
    if (facultyRepository.existsById(id)) {
      facultyRepository.deleteById(id);
      return true;
    }
    return false;
  }
  public Object getStudentsOfFaculty(Long facultyId) {
    return (Collection<Student>) (Collection<Student>) facultyRepository.findById(facultyId)
        .map(Faculty::getStudents)
        .orElse(null);
  }
}
