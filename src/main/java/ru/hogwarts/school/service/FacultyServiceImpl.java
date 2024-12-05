package ru.hogwarts.school.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

@Service
public class FacultyServiceImpl implements FacultyService {

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

  @Override
  public Faculty addFaculty(Faculty faculty) {
    return facultyRepository.save(faculty);
  }

  public Faculty editFaculty(Faculty faculty) {
    return facultyRepository.save(faculty);
  }

  @Override
  public void deleteFaculty(long id) {
    if (facultyRepository.existsById(id)) {
      facultyRepository.deleteById(id);
    }
  }

  public Collection<Student> getStudentsOfFaculty(Long id) {
    return facultyRepository.findById(id)
            .map(Faculty::getStudents)
            .orElseGet(List::of);
  }
}
