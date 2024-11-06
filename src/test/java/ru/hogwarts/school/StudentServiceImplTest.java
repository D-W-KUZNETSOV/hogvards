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
    student.setName("John Doe");
    student.setAge(20);

    Student addedStudent = studentServiceImpl.addStudent(student);
    assertNotNull(addedStudent);
    assertEquals("John Doe", addedStudent.getName());
    assertEquals(20, addedStudent.getAge());
    assertEquals(0, addedStudent.getId()); // ID должен быть 0, так как это первый студент
  }

  @Test
  public void testFindStudent() {
    Student student = new Student();
    student.setName("Jane Doe");
    student.setAge(22);
    Student addedStudent = studentServiceImpl.addStudent(student);

    Student foundStudent = studentServiceImpl.findStudent(addedStudent.getId());
    assertNotNull(foundStudent);
    assertEquals(addedStudent.getId(), foundStudent.getId());
    assertEquals("Jane Doe", foundStudent.getName());
  }

  @Test
  public void testEditStudent() {
    Student student = new Student();
    student.setName("Alice");
    student.setAge(21);
    Student addedStudent = studentServiceImpl.addStudent(student);

    addedStudent.setName("Alice Updated");
    addedStudent.setAge(22);
    Student updatedStudent = studentServiceImpl.editStudent(addedStudent);

    assertNotNull(updatedStudent);
    assertEquals("Alice Updated", updatedStudent.getName());
    assertEquals(22, updatedStudent.getAge());
  }

  @Test
  public void testEditNonExistingStudent() {
    Student student = new Student();
    student.setId(999L); // Неверный ID
    student.setName("Non-existing Student");
    student.setAge(25);

    Student updatedStudent = studentServiceImpl.editStudent(student);
    assertNull(updatedStudent); // Должно вернуть null, так как студент не существует
  }

  @Test
  public void testDeleteStudent() {
    Student student = new Student();
    student.setName("Bob");
    student.setAge(23);
    Student addedStudent = studentServiceImpl.addStudent(student);

    Student deletedStudent = studentServiceImpl.deleteStudent(addedStudent.getId());
    assertNotNull(deletedStudent);
    assertEquals(addedStudent.getId(), deletedStudent.getId());

    // Проверяем, что студент был удален
    Student foundStudent = studentServiceImpl.findStudent(addedStudent.getId());
    assertNull(foundStudent); // Должно вернуть null, так как студент был удален
  }
}

