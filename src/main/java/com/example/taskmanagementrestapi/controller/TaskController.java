package com.example.taskmanagementrestapi.controller;

import com.example.taskmanagementrestapi.entity.Task;
import com.example.taskmanagementrestapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService theTaskService){
        taskService = theTaskService;
    }

    //GET all
    @GetMapping("/tasks")
    public List<Task> findAll(){
        return taskService.findAll();
    }

    //GET by id
    @GetMapping("/tasks/{taskId}")
    public Task getTask(@PathVariable int taskId){
        Task findTask = taskService.findById(taskId);

        return findTask;
    }

    //POST create
    @PostMapping("/tasks")
    //* Return 201 Created when the method completes.
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@RequestBody Task newTask){
        validateTask(newTask);
        Task dbTask = taskService.save(newTask);

        return dbTask;
    }

    //Delete
    @DeleteMapping("/tasks/{taskId}")
    //* Return 204 No Content when the method completes.
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable int taskId){
        taskService.deleteById(taskId);
    }

    //Validation
    private void validateTask(Task newTask){
        if(newTask.getTitle() == null  || newTask.getTitle().isBlank()){
            throw new RuntimeException("Title is empty");
        }
        if(newTask.getDescription() == null || newTask.getDescription().isBlank()){
            throw new RuntimeException("Description is empty");
        }
        if(newTask.getStatus() == null){
            throw new RuntimeException("Status is not selected");
        }
        if(newTask.getPriority() == null){
            throw new RuntimeException("Priority is not selected");
        }
        if(newTask.getDueDate() == null){
            throw new RuntimeException("Due date is not entered");
        }
    }

}