package com.flowforge.task.controller;

import com.flowforge.task.entity.Task;
import com.flowforge.task.entity.TaskStatus;
import com.flowforge.task.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public Task create(
            @RequestBody Map<String, String> body,
            HttpServletRequest request
    ) {
        return service.createTask(
                body.get("title"),
                body.get("description"),
                request.getHeader("X-Org-Id"),
                request.getHeader("X-User-Id"),
                request.getHeader("X-Role")
        );
    }

    // LIST (org scoped)
    @GetMapping
    public List<Task> list(HttpServletRequest request) {
        return service.listTasks(
                request.getHeader("X-Org-Id")
        );
    }

    // VIEW
    @GetMapping("/{id}")
    public Task get(
            @PathVariable String id,
            HttpServletRequest request
    ) {
        return service.getTask(
                id,
                request.getHeader("X-Org-Id")
        );
    }

    // UPDATE STATUS
    @PatchMapping("/{id}/status")
    public Task updateStatus(
            @PathVariable String id,
            @RequestBody Map<String, String> body,
            HttpServletRequest request
    ) {
        return service.updateStatus(
                id,
                TaskStatus.valueOf(body.get("status")),
                request.getHeader("X-Org-Id"),
                request.getHeader("X-Role"),
                request.getHeader("X-User-Id")
        );
    }

    // ASSIGN
    @PatchMapping("/{id}/assign")
    public Task assign(
            @PathVariable String id,
            @RequestBody Map<String, String> body,
            HttpServletRequest request
    ) {
        return service.assignTask(
                id,
                body.get("assigneeId"),
                request.getHeader("X-Org-Id"),
                request.getHeader("X-Role")
        );
    }

    // LIST MY TASKS
    @GetMapping("/assigned/me")
    public List<Task> myTasks(HttpServletRequest request) {
        return service.listAssignedTo(
                request.getHeader("X-User-Id")
        );
    }
}
