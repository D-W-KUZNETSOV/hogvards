package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
            this.restTemplate.getForObject("http://localhost:" + port + "/student",
                    String.class))
        .isNotEmpty();
  }


  @Test
  public void testPostStudent() throws Exception {
    Student student = new Student();
    student.setId(1L);
    student.setName("Garry Potter");
    student.setAge(15);

    Assertions
            .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student",
                    student,
                    String.class))
            .isNotNull();
  }
  @Test
  public void testPutStudent() {
    Student student = new Student();
    student.setId(10);
    student.setName("Ron Uizly");
    student.setAge(15);



    // Создаем HttpEntity для запроса
    HttpEntity<Student> requestEntity = new HttpEntity<>(student);

    // Отправляем PUT-запрос
    ResponseEntity<String> response = restTemplate.exchange(
            "http://localhost:" + port + "/student/{id}",
            HttpMethod.PUT,
            requestEntity,
            String.class
    );

    // Проверяем, что статус ответа 200 OK
    assertThat(response.getStatusCodeValue()).isEqualTo(200);
    // Дополнительные проверки содержимого ответа, если это необходимо
  }


}

