package ru.hogwarts.school.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Faculty {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NonNull
  private String name;
  private String color;

  @JsonProperty(required = true)
  @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
  private Collection<Student> students;


  private Faculty(Long id, String name, String color) {
    this.id = id;
    this.name = name;
    this.color = color;
  }


  private static Faculty createFaculty(Long id, String name, String color) {
    return new Faculty(id, name, color);
  }

  public String getName() {
    return name;
  }

  public void setId(Long id) {
    this.id = id;
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


  public Collection<Student> getStudents() {
    return students;
  }

}
