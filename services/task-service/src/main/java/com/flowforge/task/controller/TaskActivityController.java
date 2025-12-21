package com.flowforge.task.controller;

import com.flowforge.task.entity.TaskActivity;
import com.flowforge.task.repository.TaskActivityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/activities")
public class TaskActivityController {

    private final TaskActivityRepository repository;

    public TaskActivityController(TaskActivityRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<TaskActivity> getActivities(@PathVariable String taskId) {
        return repository.findByTaskIdOrderByCreatedAtAsc(taskId);
    }
}
