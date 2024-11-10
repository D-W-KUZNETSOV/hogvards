package ru.hogwarts.school;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentServiceImpl;

public class StudentServiceImplTest {

  private StudentServiceImpl studentServiceImpl;

  @BeforeEach
  public void setUp() {
    studentServiceImpl = StudentServiceImpl.createStudentServiceImpl();
  }

  @Test
  public void testAddStudent() {
    Student student = new Student();
    student.setName("Garry Potter");
    student.setAge(15);

    Student addedStudent = studentServiceImpl.addStudent(student);
    assertNotNull(addedStudent);
    assertEquals("Garry Potter", addedStudent.getName());
    assertEquals(15, addedStudent.getAge());
    assertEquals(0, addedStudent.getId());
  }

  @Test
  public void testFindStudent() {
    Student student = new Student();
    student.setName("Garry Potter");
    student.setAge(14);
    Student addedStudent = studentServiceImpl.addStudent(student);

    Student foundStudent = studentServiceImpl.findStudent(addedStudent.getId());
    assertNotNull(foundStudent);
    assertEquals(addedStudent.getId(), foundStudent.getId());
    assertEquals("Garry Potter", foundStudent.getName());
  }

  @Test
  public void testEditStudent() {
    Student student = new Student();
    student.setName("Germiona Granger");
    student.setAge(14);
    Student addedStudent = studentServiceImpl.addStudent(student);

    addedStudent.setName("Germiona Granger Updated");
    addedStudent.setAge(15);
    Student updatedStudent = studentServiceImpl.editStudent(addedStudent);

    assertNotNull(updatedStudent);
    assertEquals("Germiona Granger Updated", updatedStudent.getName());
    assertEquals(15, updatedStudent.getAge());
  }

  @Test
  public void testEditNonExistingStudent() {
    Student student = new Student();
    student.setId(999L);
    student.setName("Такого студента нет");
    student.setAge(25);

    Student updatedStudent = studentServiceImpl.editStudent(student);
    assertNull(updatedStudent);
  }

  @Test
  public void testDeleteStudent() {
    Student student = new Student();
    student.setName("Ron Uizly");
    student.setAge(14);
    Student addedStudent = studentServiceImpl.addStudent(student);

    Student deletedStudent = studentServiceImpl.deleteStudent(addedStudent.getId());
    assertNotNull(deletedStudent);
    assertEquals(addedStudent.getId(), deletedStudent.getId());

    Student foundStudent = studentServiceImpl.findStudent(addedStudent.getId());
    assertNull(foundStudent);
  }
}

