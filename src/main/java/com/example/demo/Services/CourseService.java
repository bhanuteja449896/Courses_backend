package com.example.demo.Services;

import com.example.demo.Entity.Course;
import com.example.demo.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourse(String id) {
        return courseRepository.findById(id);
    }

    @Transactional
    public Course createCourse(Course course, List<String> prerequisiteIds) {
        // Validate prerequisites
        for (String pid : prerequisiteIds) {
            if (!courseRepository.existsById(pid)) {
                throw new IllegalArgumentException("Prerequisite course not found: " + pid);
            }
        }
        course.setPrerequisites(prerequisiteIds);
        return courseRepository.save(course);
    }

    @Transactional
    public void deleteCourse(String id) {
        if (courseRepository.existsByPrerequisitesContaining(id)) {
            throw new IllegalStateException("Course is a prerequisite for another course and cannot be deleted.");
        }
        courseRepository.deleteById(id);
    }
} 