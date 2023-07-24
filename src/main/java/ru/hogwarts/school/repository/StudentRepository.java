package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);

    List<Student> findByAgeBetween(int min, int max);

    List<Student> findAllByFaculty_Id(long facultyId);

    @Query(value = "SELECT COUNT(s) FROM student s", nativeQuery = true)
    Integer getAllStudents();

    @Query(value = "SELECT AVG(s.age) FROM student s", nativeQuery = true)
    Integer getAverageAgeStudents();

    @Query(value = "SELECT s FROM student s ORDER BY s.id DESC LIMIT 5", nativeQuery = true)
    List<Student> getLastFiveStudents();

}
