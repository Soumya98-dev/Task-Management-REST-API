package com.example.taskmanagementrestapi.service;

import com.example.taskmanagementrestapi.entity.Task;
import com.example.taskmanagementrestapi.exception.BadRequestException;
import com.example.taskmanagementrestapi.exception.NotFoundException;
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
            throw new NotFoundException("id not found: "+theId);
        }
    }

    @Transactional
    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Integer id, Task task) {
        Optional<Task> findTask = taskRepository.findById(id);
        if(findTask.isPresent()){
            Task existing = findTask.get();
            existing.setTitle(task.getTitle());
            existing.setDescription(task.getDescription());
            existing.setPriority(task.getPriority());
            existing.setDueDate(task.getDueDate());
            existing.setStatus(task.getStatus());
            return taskRepository.save(existing);
        } else {
            throw new NotFoundException("id not found"+id);
        }
    }

    @Override
    public Task partialUpdate(Integer id, Map<String, Object> updates) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Task> findTask = taskRepository.findById(id);
        if(findTask.isPresent()){
            taskRepository.deleteById(id);
        } else{
            throw new NotFoundException("id not found: "+id);
        }
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