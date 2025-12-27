package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Instructor;
import com.examly.springapp.service.InstructorService;


@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    @Autowired
    private InstructorService service;

    @PostMapping
    public ResponseEntity<Instructor> addInstructor(
        @RequestBody(required = false) Instructor instructor) {
        if (instructor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return service.addInstructor(instructor);
    }

    @GetMapping
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        return service.getAllInstructors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable int id) {
        return service.getInstructorById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id,
                                                       @RequestBody Instructor instructor) {
        return service.updateInstructor(id, instructor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable int id) {
        return service.deleteInstructor(id);
    }

    @GetMapping("/page/{page}/{size}")
    public Page<Instructor> getInstructorsByPage(
            @PathVariable int page,
            @PathVariable int size) {

        return service.getInstructorsWithPagination(page, size);
    }

    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<?> getInstructorBySpecialization(
            @PathVariable String specialization) {
        return service.getInstructorBySpecialization(specialization);
    }

}
