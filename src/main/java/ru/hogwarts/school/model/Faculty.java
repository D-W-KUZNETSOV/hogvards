package ru.hogwarts.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String color;

    @JsonProperty(required = true)
    @OneToMany( fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Student> students;

    public Faculty() {
    }

    private Faculty(Long id, String name, String color, Collection<Student> students) {

        this.name = name;
        this.color = color;
        this.students = students;
    }



    public static Faculty newFaculty() {
        return new Faculty();
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
