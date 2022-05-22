package com.tracker.spring.rest.dao;


import com.tracker.spring.rest.config.HibernateConfig;
import com.tracker.spring.rest.entity.Student;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@Testcontainers
@ContextConfiguration(classes = HibernateConfig.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentDAOTest {


//    @Container
//    private final PostgreSQLContainer<PostgresTestContainers> postgresqlContainer = PostgresTestContainers.getInstance();
//

    private final DAO<Student> studentDAO;
    private static Student student;

    @Autowired
    public StudentDAOTest(DAO<Student> studentDAO) {
        this.studentDAO = studentDAO;
    }

    @BeforeAll
    static void init() {
        student = new Student();
        student.setName("John");
        student.setSurname("Malkovich");
    }

    @Transactional
    @Test
    @Order(1)
    void itShouldReturnEmptyList() {
        //when
        List<Student> all = studentDAO.getAll();
        //then
        assertEquals(0, all.size());
    }

    @Transactional
    @Test
    @Order(2)
    void itShouldPutStudentToDB() {
        //when
        studentDAO.save(student);
        //then
        Student studentFromDB = studentDAO.get(1);

        assertEquals(student, studentFromDB);
    }

    @Transactional
    @Test
    @Order(3)
    void itShouldUpdateStudent() {
        //when
        String newName = "Bob";
        student.setName(newName);
        studentDAO.save(student);
        //then
        Student studentFromDB = studentDAO.get(1);

        assertEquals(newName, studentFromDB.getName());
    }

    @Transactional
    @Test
    @Order(4)
    void itShouldDeleteStudent() {
        //when
        studentDAO.delete(1);
        //then
        List<Student> all = studentDAO.getAll();

        assertEquals(0, all.size());
    }
}
