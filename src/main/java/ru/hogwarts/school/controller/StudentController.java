package ru.hogwarts.school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("student")
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        logger.info("Was invoked method for create student");

        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
        logger.info("Was invoked method for get student");

        Student student = studentService.getStudent(studentId);
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        logger.info("Was invoked method for edit student");

        Student editedStudent = studentService.createStudent(student);
        if (editedStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(editedStudent);
    }

    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable Long studentId) {
        logger.info("Was invoked method for delete student");

        studentService.deleteStudent(studentId);
    }

    @GetMapping("/findByAge/{age}")
    public ResponseEntity<Collection<Student>> getStudentByAge(@PathVariable Integer age) {
        logger.info("Was invoked method for get student by age");

        if (age > 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());

    }

    @GetMapping("/findBetween")
    public ResponseEntity<Collection<Student>> getStudentByAge(@RequestParam("min") Integer minAge,
                                                               @RequestParam("max") Integer maxAge) {
        logger.info("Was invoked method for get student between \"min\" and \"max\" age");

        if (minAge > 0 && maxAge > 0) {
            return ResponseEntity.ok(studentService.findByAgeBetween(minAge, maxAge));
        }
        return ResponseEntity.ok(Collections.emptyList());

    }

    @GetMapping("/getAll")
    public ResponseEntity<Collection<Student>> getAllStudent() {
        logger.info("Was invoked method for get all students");

        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}/faculty")
    public ResponseEntity<Faculty> findFaculty(@PathVariable("id") long id) {
        logger.info("Was invoked method to find the faculty");

        return ResponseEntity.ok(studentService.getFaculty(id));
    }

    @GetMapping("/get-amount-of-students")
    public Integer getAmountOfStudents(){
        logger.info("Was invoked method for get amount of students");

        return studentService.getAmountStudents();
    }

    @GetMapping("/get-average-age")
    public Integer getAverageAgeOfStudents(){
        logger.info("Was invoked method for get average age of students");

        return studentService.getAverageAgeOfStudents();
    }

    @GetMapping("/get-last-5")
    public ResponseEntity<Collection<Student>> getLastFiveStudents(){
        logger.info("Was invoked method for get last five students");

        return ResponseEntity.ok(studentService.getLastFiveStudents());
    }

}
