package com.scaler.deliverypartnertaskmatching.controllers;

import com.scaler.deliverypartnertaskmatching.models.Task;
import com.scaler.deliverypartnertaskmatching.repositories.TaskRepository;
import com.scaler.deliverypartnertaskmatching.services.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController
{
    private final TaskRepository taskRepository;
    private final RedisService redisService;

    public TaskController(TaskRepository taskRepository, RedisService redisService)
    {
        this.taskRepository = taskRepository;
        this.redisService = redisService;
    }

    @PostMapping("")
    public Task addTask(Task task)
    {
        this.redisService.saveTask(task);
        return task;
    }

    @GetMapping("")
    public List<Object> getAllTasks()
    {
        return this.redisService.getAllTasks();
    }
}
