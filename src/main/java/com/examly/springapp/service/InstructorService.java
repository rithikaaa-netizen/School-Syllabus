package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Instructor;
import com.examly.springapp.repository.InstructorRepo;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepo repo;

    public ResponseEntity<Instructor> addInstructor(Instructor instructor) {
        try {
            Instructor savedInstructor = repo.save(instructor);
            return new ResponseEntity<>(savedInstructor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Instructor>> getAllInstructors() {
        try {
            List<Instructor> list = repo.findAll();
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Instructor> getInstructorById(int id) {
        try {
            Instructor instructor = repo.findById(id).orElse(null);
            if (instructor == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(instructor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Instructor> updateInstructor(Long id, Instructor instructor) {
        try {
            instructor.setInstructorId(id);
            Instructor updatedInstructor = repo.save(instructor);
            return new ResponseEntity<>(updatedInstructor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteInstructor(int id) {
        try {
            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Page<Instructor> getInstructorsWithPagination(int page, int size) {
        PageRequest pageable = PageRequest.of(
                page,
                size,
                Sort.by("instructorId").ascending()
        );
        return repo.findAll(pageable);
    }

    public ResponseEntity<?> getInstructorBySpecialization(String specialization) {
        List<Instructor> list = repo.findBySpecialization(specialization);
        if (list.isEmpty()) {
            return new ResponseEntity<>(
                "No instructors found with specialization: " + specialization,
                HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
