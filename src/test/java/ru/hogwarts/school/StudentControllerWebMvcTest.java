package ru.hogwarts.school;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.FacultyServiceImpl;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;



    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private StudentServiceImpl studentServiceImpl;

    @SpyBean
    private FacultyServiceImpl facultyServiceImpl;

    @InjectMocks
    private StudentController studentController;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testPostStudent() throws Exception {

        JSONObject studentObjekt = new JSONObject();
        studentObjekt.put("name", "Garry Potter");
        studentObjekt.put("age", 15);
        studentObjekt.put("facultyId", "id");

        Faculty faculty = new Faculty();
        faculty.setName("Grifindor");
        faculty.setColor("Red");

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);


        Student student = new Student();
        student.setAge(15);
        student.setName("Garry Potter");
        student.setFaculty(faculty);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(objectMapper.writeValueAsString(student))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Garry Potter"))
                .andExpect(jsonPath("$.age").value(15))
                .andExpect(jsonPath("$.faculty.name").value("Grifindor"));


        verify(studentRepository).save(any(Student.class));
    }


    // mockMvc.perform(MockMvcRequestBuilders
    //                .get("/student/"+student.getId())
    //                .accept(MediaType.APPLICATION_JSON))
    //        .andExpect(status().isOk())
    //        .andExpect(jsonPath("$.name").value("Garry Potter"))
    //        .andExpect(jsonPath("$.age").value(15));


}



