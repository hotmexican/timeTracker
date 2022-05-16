package com.tracker.spring.rest.controller;

import com.tracker.spring.rest.entity.Student;
import com.tracker.spring.rest.exception_handling.NoSuchStudentException;
import com.tracker.spring.rest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    private final StudentService studentService;
    @Autowired
    public AdminRestController(StudentService studentService1) {
        this.studentService = studentService1;
    }

    @GetMapping("/students")
    public List<Student> showAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable int id){
        Student student = studentService.getStudent(id);
        if(student==null){
            throw new NoSuchStudentException("There is no student with id = " + id + " in Database");
        }
     return student;
    }

    @PostMapping("/students")
    public Student addNewStudent(@RequestBody Student student){
        studentService.saveStudent(student);
        return student;
    }

    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student student){
        studentService.saveStudent(student);
        return student;
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id){
        Student student = studentService.getStudent(id);
        if(student==null){
            throw new NoSuchStudentException("There is no student with id = " + id + " in Database");
        }
        studentService.deleteStudent(id);
        return "Student with id = " + id + " was deleted from Database";
    }

}
