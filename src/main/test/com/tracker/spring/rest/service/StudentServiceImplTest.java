package com.tracker.spring.rest.service;

import com.tracker.spring.rest.dao.StudentDAOImpl;
import com.tracker.spring.rest.entity.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;
    @Mock
    private StudentDAOImpl studentDAO;

    private static Student student;

    @BeforeAll
    static void init() {
        student = new Student();
        student.setName("John");
        student.setSurname("Malkovich");
    }

    @Test
    void getAll() {
        //when
        studentService.getAll();
        //then
        verify(studentDAO).getAll();
    }

    @Test
    void save() {
        //when
        studentService.save(student);
        //then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentDAO).save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();
        assertEquals(student, capturedStudent);
    }

    @Test
    void get() {
        //when
        Integer testInt = 1;
        studentService.get(testInt);
        //then
        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(studentDAO).get(integerArgumentCaptor.capture());

        Integer capturedInt = integerArgumentCaptor.getValue();
        assertEquals(testInt, capturedInt);
    }

    @Test
    void delete() {
        //when
        Integer testInt = 1;
        studentService.delete(testInt);
        //then
        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(studentDAO).delete(integerArgumentCaptor.capture());

        Integer capturedInt = integerArgumentCaptor.getValue();
        assertEquals(testInt, capturedInt);
    }
}