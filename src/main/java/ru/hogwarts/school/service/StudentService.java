package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StudentService {

    final StudentRepository studentRepository;

    private Long id = 0L;

    HashMap<Long, Student> studentHashMap = new HashMap<>();

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        student.setId(++id);
        studentHashMap.put(id, student);
        return student;
    }

    public Student getStudent(Long id) {
        if (!studentHashMap.containsKey(id)) {
            throw new StudentNotFoundException("Студент не найден.");
        }
        return studentHashMap.get(id);
    }

    public Student editStudent(Long studentId, Student student) {
        if (!studentHashMap.containsKey(studentId)) {
            throw new StudentNotFoundException("Студент не найден.");
        }
        studentHashMap.put(studentId, student);
        return student;
    }

    public Student deleteStudent(Long id) {
        if (!studentHashMap.containsKey(id)) {
            throw new StudentNotFoundException("Студент не найден.");
        }
        return studentHashMap.remove(id);
    }

    public List<Student> findByAge(int age) {

        List<Student> studentList = new ArrayList<>();
        for (Student s: studentHashMap.values()){
            if (s.getAge() == age){
                studentList.add(s);
            }
        }

        return studentList;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentHashMap.values());
    }
}
