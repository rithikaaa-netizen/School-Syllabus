package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Course;
import com.examly.springapp.repository.CourseRepo;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    public Course addCourse(Course course) {
        return courseRepo.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public Course getCourseById(int id) {
        return courseRepo.findById(id).orElse(null);
    }

    public Course updateCourse(int id, Course course) {
        course.setCourseId(id);
        return courseRepo.save(course);
    }

    public ResponseEntity<List<Course>> getCoursesByInstructor(Long instructorId) {
        try {
            List<Course> courses = courseRepo.findByInstructorInstructorId(instructorId);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getCoursesByLevel(String level) {
        List<Course> list = courseRepo.findByLevel(level);
        if (list.isEmpty()) {
            return new ResponseEntity<>(
                    "No courses found at level: " + level,
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
