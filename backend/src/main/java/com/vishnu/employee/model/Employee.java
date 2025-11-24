package com.vishnu.employee.model;

import lombok.Data;
import java.util.List;

@Data
public class Employee {
    private String name;
    private String email;
    private String department;
    private int yearsOfExperience;
    private String joinDate;
    private List<String> skills;
    private String role;
}
