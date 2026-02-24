package com.prajna.taskmanager.controller;

import com.prajna.taskmanager.Task;
import com.prajna.taskmanager.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Get all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Add new task
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }
    @PutMapping("/{id}")
public Task updateTask(@PathVariable Long id, @RequestBody Task task) {

    Task existing = taskRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found"));

    existing.setTitle(task.getTitle());
    existing.setDescription(task.getDescription());
    existing.setCompleted(task.isCompleted());

    return taskRepository.save(existing);
}

    // Delete task
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}