package com.example.taskmanagementrestapi.service;

import com.example.taskmanagementrestapi.entity.Task;
import com.example.taskmanagementrestapi.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    TaskRepository taskRepository;
    @InjectMocks
    TaskServiceImpl taskServiceImpl;

    @Test
    void saveTaskShouldSaveTaskSuccessfully(){
        Task task = new Task();
        task.setTitle("My");
        Mockito.when(taskRepository.save(task)).thenReturn(task);
        Task addedTask = taskServiceImpl.save(task);

        //Test product == matched product
        Assertions.assertEquals(task.getTitle(), addedTask.getTitle());
        Mockito.verify(taskRepository).save(task);
    }
}