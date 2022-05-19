package com.tracker.spring.rest.dao;

import com.tracker.spring.rest.entity.Student;
import com.tracker.spring.rest.entity.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDAOImpl implements DAO<Task>{

    private final SessionFactory sessionFactory;

    @Autowired
    public TaskDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Task> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Task", Task.class).getResultList();
    }

    @Override
    public void save(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(task);
    }

    @Override
    public Task get(int id) {
        Session session = sessionFactory.getCurrentSession();
        Task task = session.get(Task.class, id);
        return task;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Task> query = session.createQuery("delete from Task where id=:taskId");
        query.setParameter("taskId", id);
        query.executeUpdate();
    }
}
