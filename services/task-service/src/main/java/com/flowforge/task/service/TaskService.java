package com.flowforge.task.service;

import com.flowforge.task.entity.Task;
import com.flowforge.task.entity.TaskStatus;
import com.flowforge.task.repository.TaskRepository;
import com.flowforge.task.security.TaskAccessValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskAccessValidator validator;

    public TaskService(TaskRepository repository, TaskAccessValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    // CREATE
    public Task createTask(
            String title,
            String description,
            String orgId,
            String createdBy,
            String role
    ) {
        validator.assertCanCreate(role);

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setOrganizationId(orgId);
        task.setCreatedBy(createdBy);

        return repository.save(task);
    }

    // LIST (org scoped)
    public List<Task> listTasks(String orgId) {
        return repository.findByOrganizationId(orgId);
    }

    // VIEW
    public Task getTask(String taskId, String orgId) {
        Task task = repository.findByIdAndOrganizationId(taskId, orgId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        validator.assertCanView(task, orgId);
        return task;
    }

    // UPDATE STATUS
    public Task updateStatus(
            String taskId,
            TaskStatus status,
            String orgId,
            String role,
            String userId
    ) {
        Task task = getTask(taskId, orgId);
        validator.assertCanUpdate(task, role, userId);

        task.setStatus(status);
        return repository.save(task);
    }

    // ASSIGN
    public Task assignTask(
            String taskId,
            String assigneeId,
            String orgId,
            String role
    ) {
        validator.assertCanAssign(role);

        Task task = getTask(taskId, orgId);
        task.setAssignedTo(assigneeId);

        return repository.save(task);
    }

    public List<Task> listAssignedTo(String userId) {
        return repository.findByAssignedTo(userId);
}
}
