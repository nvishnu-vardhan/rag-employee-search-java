package com.vishnu.employee.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vishnu.employee.model.Employee;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void loadEmployees() throws IOException {
        ClassPathResource resource = new ClassPathResource("employees.json");
        employees = objectMapper.readValue(resource.getInputStream(),
                new TypeReference<List<Employee>>() {});
    }

    public List<Employee> searchEmployees(String query) {
        if (query == null || query.trim().isEmpty()) {
            return employees;
        }
        String lowerQuery = query.toLowerCase();
        String[] keywords = lowerQuery.split("\\s+");
        return employees.stream()
                .filter(emp -> matchesQuery(emp, keywords))
                .collect(Collectors.toList());
    }

    private boolean matchesQuery(Employee emp, String[] keywords) {
        String searchText = String.join(" ",
                emp.getName().toLowerCase(),
                emp.getEmail().toLowerCase(),
                emp.getDepartment().toLowerCase(),
                emp.getRole().toLowerCase(),
                String.join(" ", emp.getSkills()).toLowerCase()
        );
        for (String keyword : keywords) {
            if (keyword.matches("\\d+\\+?")) {
                int reqYears = Integer.parseInt(keyword.replace("+", ""));
                if (emp.getYearsOfExperience() < reqYears) return false;
            } else if (!searchText.contains(keyword)) {
                return false;
            }
        }
        return true;
    }

    public String getSummary(List<Employee> results) {
        if (results.isEmpty()) {
            return "No employees found matching your search criteria.";
        }
        var skillCounts = results.stream()
                .flatMap(e -> e.getSkills().stream())
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        var topSkills = skillCounts.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(", "));
        var topDept = results.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
                .entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("Various");
        return String.format("%d employees were found in the search results. " +
                        "The top results include employees with key skills in %s, primarily in the %s department.",
                results.size(), topSkills, topDept);
    }
}
