package com.tracker.spring.rest.dao;

import com.tracker.spring.rest.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements DAO<Student>{
    private final SessionFactory sessionFactory;
    @Autowired
    public StudentDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    @Override
    public List<Student> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Student", Student.class).getResultList();
    }

    @Override
    public void save(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(student);

    }

    @Override
    public Student get(int id) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, id);
        return student;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Student> query = session.createQuery("delete from Student where id=:studentId");
        query.setParameter("studentId", id);
        query.executeUpdate();
    }
}
