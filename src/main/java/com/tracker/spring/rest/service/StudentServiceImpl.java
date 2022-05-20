package com.tracker.spring.rest.service;

import com.tracker.spring.rest.dao.DAO;
import com.tracker.spring.rest.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements ServiceInterface<Student>{

    private final DAO<Student> studentDAO;

    @Autowired
    public StudentServiceImpl(DAO<Student> studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    @Transactional
    public List<Student> getAll() {
        return studentDAO.getAll();
    }

    @Override
    @Transactional
    public void save(Student student) {
        studentDAO.save(student);
    }

    @Override
    @Transactional
    public Student get(int id) {
        return studentDAO.get(id);
    }

    @Override
    @Transactional
    public void delete(int id) {
        studentDAO.delete(id);
    }
}
