package com.example.enrollment_service.Model;

import lombok.Data;

import java.sql.Date;

@Data
public class StudentCourseDTO {
    private Integer studentCourseId;
    private String studentId;
    private int courseId;
    private Date enrollmentDate;
    private StudentCourseStatus status;
    private int participationScore;

    public StudentCourseDTO(
            Integer studentCourseId,
            String studentId,
            int courseId,
             Date enrollmentDate,
             StudentCourseStatus status,
             int participationScore) {
        this.studentCourseId = studentCourseId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
        this.participationScore = participationScore;
    }
}
