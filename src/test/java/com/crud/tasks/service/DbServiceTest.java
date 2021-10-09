package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void shouldGetAllTasksTest() {
        //Given
        Task task = new Task(1L, "title", "content");
        List<Task> taskList = Arrays.asList(task);

        when(repository.findAll()).thenReturn(taskList);

        //When
        List<Task> theList = dbService.getAllTasks();

        //Then
        assertNotNull(theList);
        assertEquals(1, theList.size());
    }

    @Test
    public void shouldGetTaskWhichDoesNotExistTest() {
        //Given
        when(repository.findById(1L)).thenReturn(Optional.empty());

        //When
        Optional<Task> result = dbService.getTask(1L);

        //Then
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void shouldSaveTaskTest() {
        //Given
        Task task = new Task(1L, "title", "content");
        when(repository.save(task)).thenReturn(task);

        //When
        Task savedTask = dbService.saveTask(task);

        //Then
        assertEquals(task.getId(), savedTask.getId());
        assertEquals(task.getTitle(), savedTask.getTitle());
        assertEquals(task.getContent(), savedTask.getContent());
    }

    @Test
    void shouldDeleteTask() {
        //Given
        Task task = new Task(1L, "title", "content");

        //When
        dbService.saveTask(task);
        Long taskId = task.getId();
        dbService.deleteTask(taskId);
        Optional<Task> savedTask = dbService.getTask(taskId);

        //Then
        assertFalse(savedTask.isPresent());
    }
}