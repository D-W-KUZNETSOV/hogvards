package ru.hogwarts.school;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyServiceImpl;

public class FacultyServiceImplTest {

  private FacultyServiceImpl facultyServiceImpl;

  @BeforeEach
  public void setUp() {
    facultyServiceImpl = new FacultyServiceImpl();
  }

  @Test
  public void testAddFaculty() {
    Faculty faculty = new Faculty();
    faculty.setName("Grifindor");
    faculty.setColor("Red");

    Faculty addedFaculty = facultyServiceImpl.addFaculty(faculty);
    assertNotNull(addedFaculty);
    assertEquals("Grifindor", addedFaculty.getName());
    assertEquals("Red", addedFaculty.getColor());
    assertEquals(0, addedFaculty.getId());
  }

  @Test
  public void testFindFaculty() {
    Faculty faculty = new Faculty();
    faculty.setName("Hufflepuff");
    faculty.setColor("Black");
    Faculty addedFaculty = facultyServiceImpl.addFaculty(faculty);

    Faculty foundFaculty = facultyServiceImpl.findFaculty(addedFaculty.getId());
    assertNotNull(foundFaculty);
    assertEquals(addedFaculty.getId(), foundFaculty.getId());
    assertEquals("Hufflepuff", foundFaculty.getName());
  }

  @Test
  public void testEditFaculty() {
    Faculty faculty = new Faculty();
    faculty.setName("Ravenclaw");
    faculty.setColor("Blue");
    Faculty addedFaculty = facultyServiceImpl.addFaculty(faculty);

    addedFaculty.setName("Ravenclaw Updated");
    addedFaculty.setColor("Yellow");
    Faculty updatedFaculty = facultyServiceImpl.editFaculty(addedFaculty);

    assertNotNull(updatedFaculty);
    assertEquals("Ravenclaw Updated", updatedFaculty.getName());
    assertEquals("Yellow", updatedFaculty.getColor());
  }

  @Test
  public void testEditNonExistingFaculty() {
    Faculty faculty = new Faculty();
    faculty.setId(999L);
    faculty.setName("такого факультета нет");
    faculty.setColor("Black");

    Faculty updatedFaculty = facultyServiceImpl.editFaculty(faculty);
    assertNull(updatedFaculty);
  }


}
