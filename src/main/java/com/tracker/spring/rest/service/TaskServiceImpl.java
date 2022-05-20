package com.tracker.spring.rest.service;

import com.tracker.spring.rest.dao.DAO;
import com.tracker.spring.rest.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskServiceImpl implements ServiceInterface<Task> {

    private final DAO<Task> dao;

    @Autowired
    public TaskServiceImpl(DAO<Task> dao) {
        this.dao = dao;
    }

    @Override
    public List<Task> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(Task task) {
        dao.save(task);
    }

    @Override
    public Task get(int id) {
        return dao.get(id);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
