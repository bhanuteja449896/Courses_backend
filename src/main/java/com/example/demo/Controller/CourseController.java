package com.example.demo.Controller;

import com.example.demo.Entity.Course;
import com.example.demo.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable String id) {
        return courseService.getCourse(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Map<String, Object> payload) {
        try {
            String id = (String) payload.get("id");
            String title = (String) payload.get("title");
            String description = (String) payload.get("description");

            if (id == null || title == null || description == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("id, title, and description are required fields.");
            }

            List<String> prerequisites = new ArrayList<>();
            Object prereqObj = payload.get("prerequisites");
            if (prereqObj instanceof List<?>) {
                for (Object o : (List<?>) prereqObj) {
                    if (o != null) prerequisites.add(o.toString());
                }
            }

            Course course = new Course(id, title, description);
            course.setPrerequisites(prerequisites);

            Course saved = courseService.createCourse(course, prerequisites);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable String id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
} 