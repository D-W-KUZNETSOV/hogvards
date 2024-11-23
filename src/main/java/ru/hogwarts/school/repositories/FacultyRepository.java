package ru.hogwarts.school.repositories;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {


  Collection<Faculty> findByColorIgnoreCase(String color);

  Collection<Faculty> findByNameIgnoreCase(String name);

}




