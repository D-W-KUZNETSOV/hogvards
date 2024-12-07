package ru.hogwarts.school.controller;

import java.util.List;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.FacultyServiceImpl;

import java.util.Collection;

@Getter
@RestController
@RequestMapping("/faculty")
public class FacultyController {
  private final FacultyRepository facultyRepository;
  private final FacultyServiceImpl facultyServiceImpl;


  private final StudentRepository studentRepository;

  public FacultyController(FacultyRepository facultyRepository, FacultyServiceImpl facultyServiceImpl, StudentRepository studentRepository) {
    this.facultyRepository = facultyRepository;
    this.facultyServiceImpl = facultyServiceImpl;
    this.studentRepository = studentRepository;
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
  public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty) {
    Faculty savedFaculty = facultyRepository.save(faculty);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedFaculty);
  }

  @PutMapping("{id}")
  public Faculty updateFaculty(@PathVariable Long id,
      @RequestBody Faculty faculty) {
    faculty.setId(id);
    return facultyServiceImpl.editFaculty(faculty);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Object> deleteFaculty(@PathVariable Long id) {
    try {
      facultyServiceImpl.deleteFaculty(id);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {

      System.err.println("Ошибка при удалении факультета: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/{Id}/students")
  public List<Student> getStudentsByFacultyId(@PathVariable Long Id) {
    return studentRepository.findByFacultyId(Id);
  }

}

