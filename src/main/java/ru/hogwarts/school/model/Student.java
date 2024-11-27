package ru.hogwarts.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;


@Entity(name = "Student")

public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;
  private int age;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "faculty_id")
  @JsonIgnore
  private Faculty faculty;

  public Student(String name, int age) {
    this.name = name;
    this.age = age;
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

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", age=" + age +
        ", faculty=" + faculty +
        '}';
  }


  public Faculty getFaculty() {
    return faculty;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setFaculty(Faculty faculty) {
    this.faculty = faculty;
  }
}