package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

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

    public Collection<String> getByFirstLetter(String letter) {
        logger.info("Was invoked method for get students by first letter");

        return studentRepository.findAll().stream()
                .map(Student::getName)
                .filter(name -> name.substring(0,1).equalsIgnoreCase(letter))
                .sorted()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public Double getAverageAgeAllStudents() {
        logger.info("Was invoked method for get average age of all students");

        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }


}
