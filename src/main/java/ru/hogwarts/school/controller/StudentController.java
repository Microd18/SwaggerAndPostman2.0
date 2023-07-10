package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("student")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
        Student student = studentService.getStudent(studentId);
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {

        Student editedStudent = studentService.createStudent(student);
        if (editedStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(editedStudent);
    }

    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @GetMapping("/findByAge/{age}")
    public ResponseEntity<Collection<Student>> getStudentByAge(@PathVariable Integer age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());

    }

    @GetMapping("/findBetween")
    public ResponseEntity<Collection<Student>> getStudentByAge(@RequestParam("min") Integer minAge,
                                                               @RequestParam("max") Integer maxAge) {
        if (minAge > 0 && maxAge > 0) {
            return ResponseEntity.ok(studentService.findByAgeBetween(minAge, maxAge));
        }
        return ResponseEntity.ok(Collections.emptyList());

    }

    @GetMapping("/getAll")
    public ResponseEntity<Collection<Student>> getAllStudent() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}/faculty")
    public ResponseEntity<Faculty> findFaculty(@PathVariable("id") long id) {
        return ResponseEntity.ok(studentService.getFaculty(id));
    }

    @GetMapping("/get-amount-of-students")
    public Integer getAmountOfStudents(){
        return studentService.getAmountStudents();
    }

    @GetMapping("/get-average-age")
    public Integer getAverageAgeOfStudents(){
        return studentService.getAverageAgeOfStudents();
    }

    @GetMapping("/get-last-5")
    public ResponseEntity<Collection<Student>> getLastFiveStudents(){
        return ResponseEntity.ok(studentService.getLastFiveStudents());
    }

}
