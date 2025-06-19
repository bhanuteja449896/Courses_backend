package com.example.demo.Services;

import com.example.demo.Entity.CourseInstance;
import com.example.demo.Repository.CourseInstanceRepository;
import com.example.demo.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseInstanceService {
    @Autowired
    private CourseInstanceRepository instanceRepository;
    @Autowired
    private CourseRepository courseRepository;

    public CourseInstance createInstance(int year, int semester, String courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new IllegalArgumentException("Course not found: " + courseId);
        }
        CourseInstance instance = new CourseInstance(year, semester, courseId);
        return instanceRepository.save(instance);
    }

    public List<CourseInstance> getInstances(int year, int semester) {
        return instanceRepository.findByYearAndSemester(year, semester);
    }

    public Optional<CourseInstance> getInstance(int year, int semester, String courseId) {
        return instanceRepository.findByYearAndSemesterAndCourseId(year, semester, courseId);
    }

    public void deleteInstance(int year, int semester, String courseId) {
        instanceRepository.deleteByYearAndSemesterAndCourseId(year, semester, courseId);
    }
} 