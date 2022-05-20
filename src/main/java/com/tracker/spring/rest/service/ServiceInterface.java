package com.tracker.spring.rest.service;

import com.tracker.spring.rest.entity.Student;

import java.util.List;

public interface ServiceInterface<T>{
    public List<T> getAll();
    public void save(T t);
    public T get(int id);
    public void delete(int id);
}
