package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SchoolApplicationTest {

  @LocalServerPort
  private int port;

  @Autowired
  private StudentController studentController;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void contextLoads() throws Exception {
    Assertions.assertThat(studentController).isNotNull();
  }

  @Test
  public void testGetStudent() throws Exception {
    Assertions
        .assertThat(
            this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
        .isNotEmpty();
  }

  @Test
  public void testPostStudent() throws Exception {
    Student student = new Student();
    student.setName("Harry Potter");
    student.setAge(15);
    ResponseEntity<Student> postResponse = this.restTemplate.postForEntity(
        "http://localhost:" + port + "/student",
        student,
        Student.class
    );
    Assertions.assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

  }






  }

