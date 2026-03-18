package com.example.taskmanagementrestapi.service;

import com.example.taskmanagementrestapi.entity.Task;

import java.util.List;
import java.util.Map;

public interface TaskService {
    List<Task> findAll();
    Task findById(Integer id);
    Task save(Task task);
    Task update(Integer id, Task task);
    Task partialUpdate(Integer id, Map<String, Object> updates);
    void deleteById(Integer id);
    List<Task> findByStatus(Task.Status status);
    List<Task> findByPriority(Task.Priority priority);
}