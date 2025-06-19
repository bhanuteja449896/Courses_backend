package com.example.demo.Repository;

import com.example.demo.Entity.CourseInstance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CourseInstanceRepository extends MongoRepository<CourseInstance, String> {
    List<CourseInstance> findByYearAndSemester(int year, int semester);
    Optional<CourseInstance> findByYearAndSemesterAndCourseId(int year, int semester, String courseId);
    void deleteByYearAndSemesterAndCourseId(int year, int semester, String courseId);
} 