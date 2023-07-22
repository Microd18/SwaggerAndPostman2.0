package ru.hogwarts.school.controller;

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

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

    @GetMapping("{facultyId}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long facultyId) {
        Faculty faculty = facultyService.getFaculty(facultyId);
        return ResponseEntity.ok(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty editedFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(editedFaculty);
    }

    @DeleteMapping("{facultyId}")
    public void deleteFaculty(@PathVariable Long facultyId) {
        facultyService.deleteFaculty(facultyId);
    }

    @GetMapping("/findByColor")
    public ResponseEntity<Collection<Faculty>> getFacultyByColor(@RequestParam("color") String color) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColor(color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/filter")
    public ResponseEntity<Collection<Faculty>> getFacultyByColorOrName(@RequestParam("value") String colorOrName) {
        return ResponseEntity.ok(facultyService.findByColorOrName(colorOrName));
    }

    @GetMapping("/find")
    public ResponseEntity<Collection<Faculty>> getAllFaculties() {
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    @GetMapping("/getAll/{facultyId}")
    public ResponseEntity<Collection<Student>> getStudents(@PathVariable Long facultyId) {
        return ResponseEntity.ok(facultyService.getStudents(facultyId));
    }

    @GetMapping("/longest-name")
    public ResponseEntity<String> getLongestNameOfFaculty() {
        return ResponseEntity.ok(facultyService.getLongestNameOfFaculty());
    }

    @GetMapping("/value")
    public ResponseEntity<Integer> getValue() {
        return ResponseEntity.ok(facultyService.getValueX());
    }


}
