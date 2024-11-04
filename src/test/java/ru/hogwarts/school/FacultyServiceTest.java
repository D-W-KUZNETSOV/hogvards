package ru.hogwarts.school;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

public class FacultyServiceTest {

  private FacultyService facultyService;

  @BeforeEach
  public void setUp() {
    facultyService = new FacultyService();
  }

  @Test
  public void testAddFaculty() {
    Faculty faculty = new Faculty();
    faculty.setName("Computer Science");
    faculty.setColor("Blue");

    Faculty addedFaculty = facultyService.addFaculty(faculty);
    assertNotNull(addedFaculty);
    assertEquals("Computer Science", addedFaculty.getName());
    assertEquals("Blue", addedFaculty.getColor());
    assertEquals(0, addedFaculty.getId()); // ID должен быть 0, так как это первый факультет
  }

  @Test
  public void testFindFaculty() {
    Faculty faculty = new Faculty();
    faculty.setName("Mathematics");
    faculty.setColor("Green");
    Faculty addedFaculty = facultyService.addFaculty(faculty);

    Faculty foundFaculty = facultyService.findFaculty(addedFaculty.getId());
    assertNotNull(foundFaculty);
    assertEquals(addedFaculty.getId(), foundFaculty.getId());
    assertEquals("Mathematics", foundFaculty.getName());
  }

  @Test
  public void testEditFaculty() {
    Faculty faculty = new Faculty();
    faculty.setName("Physics");
    faculty.setColor("Red");
    Faculty addedFaculty = facultyService.addFaculty(faculty);

    addedFaculty.setName("Physics Updated");
    addedFaculty.setColor("Yellow");
    Faculty updatedFaculty = facultyService.editFaculty(addedFaculty);

    assertNotNull(updatedFaculty);
    assertEquals("Physics Updated", updatedFaculty.getName());
    assertEquals("Yellow", updatedFaculty.getColor());
  }

  @Test
  public void testEditNonExistingFaculty() {
    Faculty faculty = new Faculty();
    faculty.setId(999L); // Неверный ID
    faculty.setName("Non-existing Faculty");
    faculty.setColor("Black");

    Faculty updatedFaculty = facultyService.editFaculty(faculty);
    assertNull(updatedFaculty); // Должно вернуть null, так как факультет не существует
  }

  @Test
  public void testDeleteFaculty() {
    Faculty faculty = new Faculty();
    faculty.setName("Chemistry");
    faculty.setColor("Purple");
    Faculty addedFaculty = facultyService.addFaculty(faculty);

    Faculty deletedFaculty = facultyService.deleteFaculty(addedFaculty.getId());
    assertNotNull(deletedFaculty);
    assertEquals(addedFaculty.getId(), deletedFaculty.getId());

    // Проверяем, что факультет был удален
    Faculty foundFaculty = facultyService.findFaculty(addedFaculty.getId());
    assertNull(foundFaculty); // Должно вернуть null, так как факультет был удален
  }
}
