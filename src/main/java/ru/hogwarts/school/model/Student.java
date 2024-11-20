package ru.hogwarts.school.model;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private int age;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "faculty_id")
  private Faculty faculty;




  public Student(long id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;

  }
  public void setId(Long id) {
  }
  public Student() {

  }
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Student student = (Student) o;
    return id == student.id && age == student.age && Objects.equals(name, student.name)
        && Objects.equals(faculty, student.faculty);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, age, faculty);
  }


  public Object getFaculty() {
    return faculty;
  }
}
