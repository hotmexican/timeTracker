package com.tracker.spring.rest.dao;

import com.tracker.spring.rest.entity.Student;
import com.tracker.spring.rest.entity.Task;
import org.springframework.stereotype.Component;

import java.util.List;

public interface DAO<T> {
    public List<T> getAll();
    public void save(T t);
    public T get(int id);
    public void delete(int id);
}
