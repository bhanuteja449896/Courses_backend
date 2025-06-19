package com.example.demo.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "course_instances")
public class CourseInstance {
    @Id
    private String id;
    private int year;
    private int semester;
    private String courseId;

    public CourseInstance() {}

    public CourseInstance(int year, int semester, String courseId) {
        this.year = year;
        this.semester = semester;
        this.courseId = courseId;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
} 