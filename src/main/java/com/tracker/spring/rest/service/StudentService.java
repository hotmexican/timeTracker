package com.tracker.spring.rest.service;

import com.tracker.spring.rest.entity.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getAllStudents();
    public void saveStudent(Student student);
    public Student getStudent(int id);
    public void deleteStudent(int id);
}
