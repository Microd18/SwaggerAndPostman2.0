package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(Long id) {
        if (studentRepository.findById(id).isEmpty()) {
            throw new StudentNotFoundException(id);
        }
        return studentRepository.findById(id).get();
    }

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(student);
    }

    public List<Student> findByAge(int age) {
        return new ArrayList<>(studentRepository.findByAge(age));
    }

    public List<Student> findByAgeBetween(int minAge, int maxAge) {
        return new ArrayList<>(studentRepository.findByAgeBetween(minAge, maxAge));
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentRepository.findAll());
    }

    public Faculty getFaculty(Long id) {
        return studentRepository.findById(id).get().getFaculty();
    }





}
