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
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Setter
@Getter
public final class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @NonNull
  private String name;
  private int age;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "faculty_id", nullable = false)

  private Faculty faculty;


  private Student(long id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;

  }

  public static Student createStudent(long id, String name, int age) {
    return new Student(id, name, age);
  }

  public void setId(Long id) {
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

  public Long getId() {
    return id;
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

  public String getFaculty() {
    return String.valueOf(faculty);
  }

  public void setFaculty(Faculty faculty) {
    this.faculty = faculty;
  }
}
