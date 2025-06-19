package com.example.demo.Controller;

import com.example.demo.Entity.CourseInstance;
import com.example.demo.Services.CourseInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/instances")
public class CourseInstanceController {
    @Autowired
    private CourseInstanceService instanceService;

    @PostMapping
    public ResponseEntity<?> createInstance(@RequestBody Map<String, Object> payload) {
        try {
            int year = (int) payload.get("year");
            int semester = (int) payload.get("semester");
            String courseId = (String) payload.get("courseId");
            CourseInstance instance = instanceService.createInstance(year, semester, courseId);
            return ResponseEntity.status(HttpStatus.CREATED).body(instance);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{year}/{semester}")
    public List<CourseInstance> getInstances(@PathVariable int year, @PathVariable int semester) {
        return instanceService.getInstances(year, semester);
    }

    @GetMapping("/{year}/{semester}/{courseId}")
    public ResponseEntity<CourseInstance> getInstance(
            @PathVariable int year,
            @PathVariable int semester,
            @PathVariable String courseId) {
        return instanceService.getInstance(year, semester, courseId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{year}/{semester}/{courseId}")
    public ResponseEntity<?> deleteInstance(
            @PathVariable int year,
            @PathVariable int semester,
            @PathVariable String courseId) {
        instanceService.deleteInstance(year, semester, courseId);
        return ResponseEntity.noContent().build();
    }
} 