package com.example.taskmanagementrestapi.service;

import com.example.taskmanagementrestapi.entity.Task;
import com.example.taskmanagementrestapi.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    //injecting TaskRepository (TaskServiceImpl -> TaskRepository)
    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository theTaskRepository){
        taskRepository = theTaskRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Integer theId) {
        Optional<Task> theTask =  taskRepository.findById(theId);
        if(theTask.isPresent()){
            return theTask.get();
        }
        else{
            throw new RuntimeException("id not found: "+theId);
        }
    }

    @Transactional
    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Integer id, Task task) {
        return null;
    }

    @Override
    public Task partialUpdate(Integer id, Map<String, Object> updates) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        if(id != null)  taskRepository.deleteById(id);
        else            throw new RuntimeException("Id not found");
    }

    @Override
    public List<Task> findByStatus(Task.Status status) {
        return taskRepository.findByStatus(status);
    }

    @Override
    public List<Task> findByPriority(Task.Priority priority) {
        return taskRepository.findByPriority(priority);
    }
}