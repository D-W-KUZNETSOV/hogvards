package ru.hogwarts.school.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

@Service
public final class StudentServiceImpl implements StudentService {

  private final HashMap<Long, Student> students = new HashMap<>();
  private long count = 0;


  public static StudentServiceImpl createStudentServiceImpl() {
    return new StudentServiceImpl();
  }


  public Student addStudent(Student student) {
    student.setId(count++);
    students.put(student.getId(), student);
    return student;
  }

  @Override
  public Student findStudent(long id) {
    return students.get(id);
  }


  public Student editStudent(Student student) {
    if (!students.containsKey(student.getId())) {
      return null;
    }
    students.put(student.getId(), student);
    return student;
  }

  @Override
  public Student deleteStudent(long id) {
    return students.remove(id);
  }

  public Collection<Student> findByAge(int age) {
    ArrayList<Student> result = new ArrayList<>();
    for (Student student : students.values()) {
      if (student.getAge() == age) {
        result.add(student);
      }
    }
    return result;
  }
}
