package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");

        return studentRepository.save(student);
    }

    public Student getStudent(Long id) {
        logger.info("Was invoked method for get student");

        if (studentRepository.findById(id).isEmpty()) {
            throw new StudentNotFoundException(id);
        }
        return studentRepository.findById(id).get();
    }

    public void deleteStudent(Long id) {
        logger.info("Was invoked method for delete student");

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(student);
    }

    public List<Student> findByAge(int age) {
        logger.info("Was invoked method for find by age student");

        return new ArrayList<>(studentRepository.findByAge(age));
    }

    public List<Student> findByAgeBetween(int minAge, int maxAge) {
        logger.info("Was invoked method for get student between \"min\" and \"max\" age");

        return new ArrayList<>(studentRepository.findByAgeBetween(minAge, maxAge));
    }

    public List<Student> getAllStudents() {
        logger.info("Was invoked method for get all students");

        return new ArrayList<>(studentRepository.findAll());
    }

    public Faculty getFaculty(Long id) {
        logger.info("Was invoked method for get faculty for student");

        return studentRepository.findById(id).get().getFaculty();
    }

    public Integer getAmountStudents() {
        logger.info("Was invoked method for get amount of students");

        return studentRepository.getAllStudents();
    }

    public Integer getAverageAgeOfStudents() {
        logger.info("Was invoked method for get average age of students");

        return studentRepository.getAverageAgeStudents();
    }

    public List<Student> getLastFiveStudents() {
        logger.info("Was invoked method for get last five students");

        return studentRepository.getLastFiveStudents();
    }


}
