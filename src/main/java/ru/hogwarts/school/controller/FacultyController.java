package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
  private final FacultyServiceImpl facultyServiceImpl;

  @Autowired
  public FacultyController(FacultyServiceImpl facultyServiceImpl) {
    this.facultyServiceImpl = facultyServiceImpl;
  }

  @GetMapping("{id}")
  public Faculty getFacultyInfo(@PathVariable Long id) {
    return facultyServiceImpl.findFaculty(id).orElse(null);
  }

  @GetMapping
  public Collection<Faculty> findFaculties(@RequestParam(required = false) String color,
      @RequestParam(required = false) String name) {
    if (color != null && !color.isBlank()) {
      return facultyServiceImpl.findByColor(color);
    }
    if (name != null && !name.isBlank()) {
      return facultyServiceImpl.findByName(name);
    }
    return facultyServiceImpl.findAll();
  }

  @PostMapping
  public Faculty createFaculty(@RequestBody Faculty faculty) {
    return facultyServiceImpl.addFaculty(faculty);
  }

  @PutMapping("{id}")
  public Faculty updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
    faculty.setId(id);
    return facultyServiceImpl.editFaculty(faculty);
  }

  @DeleteMapping("{id}")
  public void deleteFaculty(@PathVariable Long id) {
    facultyServiceImpl.deleteFaculty(id);
  }
  @GetMapping("{id}/students")
  public Object getStudentsOfFaculty(@PathVariable Long id) {
    return facultyServiceImpl.getStudentsOfFaculty(id);
  }
}

