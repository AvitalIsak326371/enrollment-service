package com.example.enrollment_service.Model;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class Student {

    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private StudentStatus status;
    private BigDecimal debt;
    private String mainPhone;
    private String extraPhone;
}

