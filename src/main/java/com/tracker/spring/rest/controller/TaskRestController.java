package com.tracker.spring.rest.controller;

import com.tracker.spring.rest.entity.Student;
import com.tracker.spring.rest.entity.Task;
import com.tracker.spring.rest.service.ServiceInterface;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TaskRestController {
    private final ServiceInterface<Task> taskService;

    @Autowired
    public TaskRestController(ServiceInterface<Task> taskService) {
        this.taskService = taskService;
    }

    @ApiOperation("method to add new student")
    @PostMapping("/tasks")
    public Task addNewTask(@RequestBody Task task) {
        taskService.save(task);
        return task;
    }
}
