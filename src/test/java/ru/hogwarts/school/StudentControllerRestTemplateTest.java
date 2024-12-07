package ru.hogwarts.school;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;


import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Nested
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerRestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FacultyRepository facultyRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void testDeserializeStudents() throws Exception {
        String jsonString = "[{\"id\":19,\"name\":\"Germiona Granger\",\"age\":14}]"; // Пример JSON

        // Десериализация JSON в List<Student>
        List<Student> students = objectMapper.readValue(jsonString, new TypeReference<List<Student>>() {});

        // Проверка, что список не пуст и содержит ожидаемые данные
        assertNotNull(students);
        assertFalse(students.isEmpty());
        assertEquals(19, students.get(0).getId());
        assertEquals("Germiona Granger", students.get(0).getName());
        assertEquals(14, students.get(0).getAge());
    }

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
        student.setName("Tanya Grotter");
        student.setAge(15);

        Faculty faculty = new Faculty();
        faculty.setId(3L);
        faculty.setName("Huflepuff");
        student.setFaculty(faculty);

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student",
                        student,
                        String.class))
                .isNotNull();
    }

    @Test
    public void testPutStudent() {

        Student anotherStudent;
        anotherStudent = this.restTemplate.getForObject("http://localhost:" + port + "/student/19"
                , Student.class);

        assertThat(anotherStudent).isNotNull();

        anotherStudent.setName("Tanya Grotter");
        anotherStudent.setAge(11);
        Faculty anotherFaculty = new Faculty();
        anotherFaculty.setId(5L);
        anotherFaculty.setName("Purga");
        anotherStudent.setFaculty(anotherFaculty);

        this.restTemplate.put("http://localhost:" + port + "/student/", anotherStudent);

        Student student = this.restTemplate.getForObject("http://localhost:" + port + "/student/19", Student.class);

        assertThat(anotherStudent).isNotNull();
        assertThat(anotherStudent.getId()).isEqualTo(anotherStudent.getId());
        assertThat(anotherStudent.getName()).isEqualTo("Tanya Grotter");
        assertThat(anotherStudent.getAge()).isEqualTo(11);
        assertThat(anotherStudent.getFaculty().getId()).isEqualTo(5L);
        assertThat(anotherStudent.getFaculty().getName()).isEqualTo("Purga");
    }


    @Test
    public void testDeleteStudent() {

        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Grifindor");
        facultyRepository.save(faculty);

        Student student;
        student = new Student();
        student.setName("Hanna Abbot");
        student.setAge(16);
        student.setFaculty(faculty);

        Student saveStudent = studentController.saveStudent(student);

        assertThat(saveStudent).isNotNull();
        assertThat(saveStudent.getId()).isNotNull();


        this.restTemplate.delete("http://localhost:" + port + "/student/" + saveStudent.getId(), Student.class);

        ResponseEntity<Student> response = this.restTemplate.getForEntity("http://localhost:" + port + "/student/" + saveStudent.getId(), Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetStudentsByAgeBetween() {
        ResponseEntity<List<Student>> response = restTemplate.exchange(
                "/students/age-between?minAge=10&maxAge=20",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>() {}
        );

        // Проверка статуса ответа
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Проверка, что тело ответа не null и не пустое
        List<Student> students = response.getBody();
        assertNotNull(students);
        assertFalse(students.isEmpty());

        // Дополнительные проверки (например, проверка возраста студентов)
        for (Student student : students) {
            assertTrue(student.getAge() >= 10 && student.getAge() <= 20,
                    "Student age is out of range: " + student.getAge());
        }

        // Логирование студентов для отладки
        students.forEach(student ->
                System.out.println("Student: " + student.getName() + ", Age: " + student.getAge()));
    }
}



