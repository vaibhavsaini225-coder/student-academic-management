package com.kiet.student.controller;

import com.kiet.student.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    // In-memory storage; no database is used for this assessment.
    private final List<Student> students = new ArrayList<>();

    public StudentController() {
        students.add(new Student(101, "Rahul Sharma", "rahul@kiet.edu", "CSE(AI & ML)", 2, 8.65));
        students.add(new Student(102, "Priya Verma", "priya@kiet.edu", "CSE", 2, 9.10));
        students.add(new Student(103, "Aman Singh", "aman@kiet.edu", "CSE", 3, 8.90));
        students.add(new Student(104, "Neha Gupta", "neha@kiet.edu", "ECE", 2, 9.20));
        students.add(new Student(105, "Arjun Kumar", "arjun@kiet.edu", "CSE(AI)", 2, 8.45));
    }

    // Q3(A): GET /students
    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    // Q3(B): GET /students/{studentId}
    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudentById(@PathVariable int studentId) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return ResponseEntity.ok(student);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Student not found"));
    }

    // Q4: POST /students
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    // Q5(A): PUT /students/{studentId}
    @PutMapping("/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable int studentId,
                                           @RequestBody Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId() == studentId) {
                // The ID in the URL is treated as the authoritative ID.
                updatedStudent.setStudentId(studentId);
                students.set(i, updatedStudent);
                return ResponseEntity.ok(updatedStudent);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Student not found"));
    }

    // Q5(B): DELETE /students/{studentId}
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable int studentId) {
        boolean removed = students.removeIf(student -> student.getStudentId() == studentId);

        if (removed) {
            return ResponseEntity.ok(Map.of("message", "Student deleted successfully"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Student not found"));
    }

    // Q6(A): GET /students/search?branch=CSE&year=2
    @GetMapping("/search")
    public List<Student> searchStudents(@RequestParam("branch") String branch,
                                        @RequestParam("year") int year) {
        return students.stream()
                .filter(student -> student.getBranch() != null
                        && student.getBranch().equalsIgnoreCase(branch.trim())
                        && student.getYear() == year)
                .collect(Collectors.toList());
    }

    // Q6(B): GET /students/topper?cgpa=9.0
    @GetMapping("/topper")
    public List<Student> getStudentsByMinimumCgpa(@RequestParam("cgpa") double cgpa) {
        return students.stream()
                .filter(student -> student.getCgpa() >= cgpa)
                .collect(Collectors.toList());
    }
}
