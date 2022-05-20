package com.tracker.spring.rest.controller;

import com.tracker.spring.rest.entity.Student;
import com.tracker.spring.rest.exception_handling.NoSuchElementException;
import com.tracker.spring.rest.service.StudentServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

class AdminRestControllerTest {

    @InjectMocks
    private AdminRestController adminRestController;
    @Mock
    private StudentServiceImpl studentService;

    private static Student student;

    @BeforeAll
    static void init() {
        student = new Student();
        student.setName("John");
        student.setSurname("Malkovich");
    }


    @Test
    void showAllStudents() {
        //when
        adminRestController.showAllStudents();
        //then
        verify(studentService).getAll();
    }

    @Test
    void getStudent() {
        //when
        Integer testInt = 1;
        Mockito.when(studentService.get(testInt)).thenReturn(student);
        adminRestController.getStudent(testInt);
        //then
        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(studentService).get(integerArgumentCaptor.capture());
        Integer capturedInt = integerArgumentCaptor.getValue();
        assertEquals(testInt, capturedInt);
    }

    @Test
    void itShouldThrowNoSuchElementExceptionWhenTryToGetStudent(){
        NoSuchElementException thrown = assertThrows(
                NoSuchElementException.class, () -> {
                    Integer testInt = 1;
                    adminRestController.getStudent(testInt);
                    ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
                    verify(studentService).get(integerArgumentCaptor.capture());
                    Integer capturedInt = integerArgumentCaptor.getValue();
                    assertEquals(testInt, capturedInt);
                }

        );
        assertEquals("There is no student with id = 1 in Database", thrown.getMessage());
    }


    @Test
    void addNewStudent() {
        //when
        adminRestController.addNewStudent(student);
        //then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentService).save(studentArgumentCaptor.capture());

        Student studentArgumentCaptorValue = studentArgumentCaptor.getValue();
        assertEquals(student, studentArgumentCaptorValue);
    }

    @Test
    void updateStudent() {
        //when
        adminRestController.updateStudent(student);
        //then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentService).save(studentArgumentCaptor.capture());

        Student studentArgumentCaptorValue = studentArgumentCaptor.getValue();
        assertEquals(student, studentArgumentCaptorValue);
    }

    @Test
    void deleteStudent() {
        //when
        Integer id = 1;
        Mockito.when(studentService.get(id)).thenReturn(student);
        adminRestController.deleteStudent(id);
        //then
        ArgumentCaptor<Integer> idArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(studentService).delete(idArgumentCaptor.capture());

        Integer idArgumentCaptorValue = idArgumentCaptor.getValue();
        assertEquals(id, idArgumentCaptorValue);
    }

    @Test
    void itShouldThrowNoSuchElementExceptionWhenTryToDeleteStudent(){
        NoSuchElementException thrown = assertThrows(
                NoSuchElementException.class, () -> {
                    Integer id = 1;
                    adminRestController.deleteStudent(id);
                    ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
                    verify(studentService).get(integerArgumentCaptor.capture());
                    Integer capturedInt = integerArgumentCaptor.getValue();
                    assertEquals(id, capturedInt);
                }

        );
        assertEquals("There is no student with id = 1 in Database", thrown.getMessage());
    }
}