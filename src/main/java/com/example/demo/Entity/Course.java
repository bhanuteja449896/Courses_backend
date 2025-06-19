package com.example.demo.Entity;


public class Course {

    @Id
    private String id;
    private String description;
    private String title;
    private List<String> prerequisites = new ArrayList<>();
    
    
    
}
