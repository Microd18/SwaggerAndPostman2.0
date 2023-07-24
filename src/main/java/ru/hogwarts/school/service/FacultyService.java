package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FacultyService {

    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");

        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(Long id) {
        logger.info("Was invoked method for get faculty");

        if (facultyRepository.findById(id).isEmpty()) {
            throw new FacultyNotFoundException(id);
        }
        return facultyRepository.findById(id).get();
    }

    public void deleteFaculty(Long id) {
        logger.info("Was invoked method for delete faculty");

        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new FacultyNotFoundException(id));
        facultyRepository.delete(faculty);
    }

    public List<Faculty> findByColor(String color) {
        logger.info("Was invoked method for find by color faculty");

        return facultyRepository.findByColorIgnoreCase(color);
    }

    public List<Faculty> findByColorOrName(String colorOrName) {
        logger.info("Was invoked method for find by color or name faculty");

        return facultyRepository.findAllByColorContainingIgnoreCaseOrNameContainingIgnoreCase(colorOrName, colorOrName);
    }

    public List<Faculty> getAllFaculties() {
        logger.info("Was invoked method for get all faculties");

        return new ArrayList<>(facultyRepository.findAll());
    }

    public List<Student> getStudents(Long facultyId) {
        logger.info("Was invoked method for get students on faculty");

        return new ArrayList<>(studentRepository.findAllByFaculty_Id(facultyId));
    }

    public String getLongestNameOfFaculty() {
        logger.info("Was invoked method for get longest name of faculty");

        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparing(String::length))
                .orElseThrow();
    }

    public Integer getValueX() {
        logger.info("Was invoked method for get valueX");

        long start = System.currentTimeMillis();
        int sum = Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, Integer::sum);
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("Время подсчета с помощью стрима, мс: " + elapsed + " " + sum);

        long start2 = System.currentTimeMillis();
        int sum2 = 0;
        for (int i = 0; i <= 1_000_000; i++){
            sum2 += i;
        }
        long finish2 = System.currentTimeMillis();
        long elapsed2 = finish2 - start2;
        System.out.println("Время подсчета с помощью цикла, мс: " + elapsed2 + " " + sum2);

        return Math.max(sum, sum2);
    }
}
