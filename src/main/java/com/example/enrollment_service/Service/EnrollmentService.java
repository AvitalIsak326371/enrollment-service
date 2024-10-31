package com.example.enrollment_service.Service;

import com.example.enrollment_service.Model.Student;
import com.example.enrollment_service.Model.StudentCourseDTO;
import com.example.enrollment_service.Model.StudentCourseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    private final RestTemplate restTemplate;

    @Value("${coursesService}")
    private String coursesService;

    @Value("${studentsService}")
    private String studentsService;

    @Value("${addStudent}")
    private String addStudent;

    @Value("${addCoursesListForStudent}")
    private String addCoursesListForStudent;

    @Autowired
    public EnrollmentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> addCoursesList(Student student, List<Integer> courses, Date enrollmentDate, StudentCourseStatus status) {

        // Create URL for adding student
        String studentsUrl = studentsService + addStudent;

        // Add student
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Student> studentRequest = new HttpEntity<>(student, headers);
        ResponseEntity<Student> studentResponse = restTemplate.exchange(
                studentsUrl,
                HttpMethod.POST,
                studentRequest,
                Student.class
        );
        // Check if student was added successfully
        if (studentResponse.getStatusCode().is2xxSuccessful()) {
            // Create list of StudentCourseDTO
            List<StudentCourseDTO> studentCourses = courses.stream()
                    .map(courseId -> new StudentCourseDTO(null,
                            studentResponse.getBody().getStudentId(), courseId, enrollmentDate, status, 0))
                    .collect(Collectors.toList());
            String coursesUrl = coursesService + addCoursesListForStudent;
            HttpEntity<List<StudentCourseDTO>> coursesRequest = new HttpEntity<>(studentCourses, headers);
            ResponseEntity<Void> coursesResponse = restTemplate.exchange(
                    coursesUrl,
                    HttpMethod.POST,
                    coursesRequest,
                    Void.class
            );
            if (coursesResponse.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok("Student and courses added successfully");
            } else {
                return ResponseEntity.status(coursesResponse.getStatusCode())
                        .body("Failed to add courses for the student");
            }
        }
        return ResponseEntity.status(studentResponse.getStatusCode())
                .body("Failed to add student");
    }
}
