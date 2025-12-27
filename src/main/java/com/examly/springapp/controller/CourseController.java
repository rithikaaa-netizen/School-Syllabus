package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Course;
import com.examly.springapp.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course saved = courseService.addCourse(course);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable int id) {
        return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable int id,
            @RequestBody Course course) {
        Course updated = courseService.updateCourse(id, course);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/instructor/{id}")
    public ResponseEntity<List<Course>> getCoursesByInstructor(
            @PathVariable Long id) {
        return courseService.getCoursesByInstructor(id);
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<?> getCoursesByLevel(@PathVariable String level) {
        return courseService.getCoursesByLevel(level);
    }
    
}
