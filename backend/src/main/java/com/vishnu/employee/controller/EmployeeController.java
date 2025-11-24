package com.vishnu.employee.controller;

import com.vishnu.employee.model.Employee;
import com.vishnu.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/search")
    public Map<String, Object> searchEmployees(@RequestParam(required = false) String query) {
        List<Employee> results = employeeService.searchEmployees(query);
        String summary = employeeService.getSummary(results);

        Map<String, Object> response = new HashMap<>();
        response.put("employees", results);
        response.put("summary", summary);
        response.put("count", results.size());
        return response;
    }
}
