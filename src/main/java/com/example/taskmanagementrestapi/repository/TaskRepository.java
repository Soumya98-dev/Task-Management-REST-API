package com.example.taskmanagementrestapi.repository;

import com.example.taskmanagementrestapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    //some extra useful endpoints
    List<Task> findByStatus(Task.Status status);
    List<Task> findByPriority(Task.Priority priority);
}