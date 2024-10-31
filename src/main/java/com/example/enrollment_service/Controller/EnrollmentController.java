package com.example.enrollment_service.Controller;

import com.example.enrollment_service.Model.Student;
import com.example.enrollment_service.Model.StudentCourseStatus;
import com.example.enrollment_service.Model.StudentCoursesRequest;
import com.example.enrollment_service.Service.EnrollmentService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;


@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }
    @PostMapping("/addCoursesListForStudent")
    public ResponseEntity<Void> addCoursesListForStudent(@RequestBody StudentCoursesRequest request) {
        Student student = request.getStudent();
        List<Integer> courses = request.getCourses();
        Date enrollmentDate = request.getEnrollmentDate();
        StudentCourseStatus status = request.getStatus();

        System.out.println("student: " + student + ", course ids: " + courses + ", date: " + enrollmentDate + ", status: " + status);

        enrollmentService.addCoursesList(student, courses, enrollmentDate, status);
        return ResponseEntity.ok().build();
    }





}
