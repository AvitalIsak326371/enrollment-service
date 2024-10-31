package com.example.enrollment_service.Model;


import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class StudentCoursesRequest {

    private Student student;
    private List<Integer> courses;
    private Date enrollmentDate;
    private StudentCourseStatus status;

    public StudentCoursesRequest() {
    }
}

