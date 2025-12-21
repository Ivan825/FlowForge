package com.flowforge.task.service;

import com.flowforge.task.entity.TaskActivity;
import com.flowforge.task.repository.TaskActivityRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskActivityService {

    private final TaskActivityRepository repository;

    public TaskActivityService(TaskActivityRepository repository) {
        this.repository = repository;
    }

    public void log(
            String taskId,
            String orgId,
            String actorId,
            String action,
            String details
    ) {
        TaskActivity activity = new TaskActivity();
        activity.setTaskId(taskId);
        activity.setOrganizationId(orgId);
        activity.setActorId(actorId);
        activity.setAction(action);
        activity.setMessage("Task created");


        repository.save(activity);
    }
}
