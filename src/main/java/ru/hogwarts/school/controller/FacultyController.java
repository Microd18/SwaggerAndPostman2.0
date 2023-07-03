package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    FacultyService facultyService;

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

    @GetMapping("/find/{facultyId}")
    public Collection<Faculty> getAllFaculties() {
        return facultyService.getAllFaculties();
    }

    @GetMapping("/getAll/{facultyId}")
    public Collection<Student> getStudents(@PathVariable Long facultyId) {
        return facultyService.getStudents(facultyId);
    }

}
