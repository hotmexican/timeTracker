package com.tracker.spring.rest.controller;

import com.tracker.spring.rest.entity.Student;
import com.tracker.spring.rest.exception_handling.NoSuchElementException;
import com.tracker.spring.rest.service.ServiceInterface;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    private final ServiceInterface<Student> studentService;

    @Autowired
    public AdminRestController(ServiceInterface<Student> studentService) {
        this.studentService = studentService;
    }

    @ApiOperation("method to get all students")
    @GetMapping("/students")
    public List<Student> showAllStudents() {
        return studentService.getAll();
    }

    @ApiOperation("method to get student by id")
    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable int id) {
        Student student = studentService.get(id);
        if (student == null) {
            throw new NoSuchElementException("There is no student with id = " + id + " in Database");
        }
        return student;
    }

    @ApiOperation("method to add new student")
    @PostMapping("/students")
    public Student addNewStudent(@RequestBody Student student) {
        studentService.save(student);
        return student;
    }

    @ApiOperation("method to change student")
    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student student) {
        studentService.save(student);
        return student;
    }

    @ApiOperation("method to delete student by id")
    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id) {
        Student student = studentService.get(id);
        if (student == null) {
            throw new NoSuchElementException("There is no student with id = " + id + " in Database");
        }
        studentService.delete(id);
        return "Student with id = " + id + " was deleted from Database";
    }

}
