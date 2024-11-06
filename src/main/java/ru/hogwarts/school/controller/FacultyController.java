package ru.hogwarts.school.controller;

import java.util.Collection;
import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyServiceImpl;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

  private final FacultyServiceImpl facultyServiceImpl;

  public FacultyController(FacultyServiceImpl facultyServiceImpl) {
    this.facultyServiceImpl = facultyServiceImpl;
  }

  @GetMapping("{id}")
  public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
    Faculty faculty = facultyServiceImpl.findFaculty(id);
    if (faculty == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(faculty);
  }

  @PostMapping
  public Faculty createFaculty(@RequestBody Faculty faculty) {
    return facultyServiceImpl.addFaculty(faculty);
  }

  @PutMapping
  public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
    Faculty foundFaculty = facultyServiceImpl.editFaculty(faculty);
    if (foundFaculty == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    return ResponseEntity.ok(foundFaculty);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
    facultyServiceImpl.deleteFaculty(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<Collection<Faculty>> findFaculties(
      @RequestParam(required = false) String color) {
    if (color != null && !color.isBlank()) {
      return ResponseEntity.ok(facultyServiceImpl.findByColor(color));
    }
    return ResponseEntity.ok(Collections.emptyList());
  }


}

