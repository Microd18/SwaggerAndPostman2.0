package ru.hogwarts.school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        logger.info("Was invoked method for create faculty");

        Faculty createdFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

    @GetMapping("{facultyId}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long facultyId) {
        logger.info("Was invoked method for get faculty");

        Faculty faculty = facultyService.getFaculty(facultyId);
        return ResponseEntity.ok(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        logger.info("Was invoked method for edit faculty");

        Faculty editedFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(editedFaculty);
    }

    @DeleteMapping("{facultyId}")
    public void deleteFaculty(@PathVariable Long facultyId) {
        logger.info("Was invoked method for delete faculty");

        facultyService.deleteFaculty(facultyId);
    }

    @GetMapping("/findByColor")
    public ResponseEntity<Collection<Faculty>> getFacultyByColor(@RequestParam("color") String color) {
        logger.info("Was invoked method for get by color faculty");

        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColor(color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/filter")
    public ResponseEntity<Collection<Faculty>> getFacultyByColorOrName(@RequestParam("value") String colorOrName) {
        logger.info("Was invoked method for get faculty by color or name");

        return ResponseEntity.ok(facultyService.findByColorOrName(colorOrName));

    }

    @GetMapping("/find")
    public ResponseEntity<Collection<Faculty>> getAllFaculties() {
        logger.info("Was invoked method for get all faculties");

        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    @GetMapping("/getAll/{facultyId}")
    public ResponseEntity<Collection<Student>> getStudents(@PathVariable Long facultyId) {
        logger.info("Was invoked method for get students on faculty");

        return ResponseEntity.ok(facultyService.getStudents(facultyId));
    }

}
