package ru.hogwarts.school.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private FacultyRepository facultyRepository;

    @MockBean
    private AvatarRepository avatarRepository;

    @SpyBean
    private StudentService studentService;

    @SpyBean
    private FacultyService facultyService;

    @SpyBean
    private AvatarService avatarService;

    @Test
    public void createStudentTest() throws Exception {
        long id = 1L;
        String name = "testName";
        int age = 18;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    public void getStudentTest() throws Exception {
        long id = 1L;
        String name = "testName";
        int age = 18;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/1")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    public void editStudentTest() throws Exception {
        long id = 1L;
        String name = "testName";
        int age = 18;

        long id2 = 1L;
        String name2 = "testName2";
        int age2 = 19;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));

        JSONObject studentObject2 = new JSONObject();
        studentObject2.put("name", name2);
        studentObject2.put("age", age2);

        Student student2 = new Student();
        student2.setId(id2);
        student2.setName(name2);
        student2.setAge(age2);

        when(studentRepository.save(any(Student.class))).thenReturn(student2);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student2));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(studentObject2.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id2))
                .andExpect(jsonPath("$.name").value(name2))
                .andExpect(jsonPath("$.age").value(age2));
    }

    @Test
    public void deleteStudentTest() throws Exception {
        when(studentRepository.findById(eq(2L))).thenReturn(Optional.empty());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/student/2")
                ).andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> {
                    String responseString = result.getResponse().getContentAsString();
                    assertThat(responseString).isNotNull();
                    assertThat(responseString).isEqualTo("Студент с id = 2 не найден!");
                });
    }

    @Test
    public void findByAgeStudentTest() throws Exception {
        long id = 1L;
        String name = "testName";
        int age = 18;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("age", age);

        JSONArray jsonArray= new JSONArray();
        jsonArray.put(jsonObject);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findByAge(any(Integer.class))).thenReturn(studentList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/findByAge/18")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonArray.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void getFacultyTest() throws Exception {
        long id = 1L;
        String name = "testName";
        int age = 18;

        long idF = 1L;
        String nameF = "facultyName";
        String colorF = "color";


        JSONObject studentObject = new JSONObject();
        studentObject.put("id", idF);
        studentObject.put("name", nameF);
        studentObject.put("color", colorF);

        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName(nameF);
        faculty.setColor(colorF);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student.setFaculty(faculty);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/1/faculty")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(idF))
                .andExpect(jsonPath("$.name").value(nameF))
                .andExpect(jsonPath("$.color").value(colorF));
    }

    @Test
    public void findBetweenTest() throws Exception {
        long id = 1L;
        String name = "testName";
        int age = 18;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findByAgeBetween(any(Integer.class), any(Integer.class))).thenReturn(studentList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/findBetween?min=10&max=20")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}