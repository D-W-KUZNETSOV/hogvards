package ru.hogwarts.school.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "faculties")
@Setter
@Getter
public class Faculty {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  private long id;
  private String name;
  private String color;


  private Faculty(long id, String name, String color) {
    this.id = id;
    this.name = name;
    this.color = color;
  }




  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Faculty faculty = (Faculty) o;
    return id == faculty.id && Objects.equals(name, faculty.name) && Objects.equals(color,
        faculty.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, color);
  }

  @Override
  public String toString() {
    return "Faculty{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", color='" + color + '\'' +
        '}';
  }

}