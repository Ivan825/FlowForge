package com.flowforge.task.service;

import com.flowforge.task.entity.Task;
import com.flowforge.task.entity.TaskComment;
import com.flowforge.task.repository.TaskCommentRepository;
import com.flowforge.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskCommentService {

    private final TaskRepository taskRepository;
    private final TaskCommentRepository commentRepository;

    public TaskCommentService(
            TaskRepository taskRepository,
            TaskCommentRepository commentRepository
    ) {
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
    }

    public TaskComment addComment(
            String taskId,
            String message,
            String orgId,
            String userId
    ) {
        Task task = taskRepository
                .findByIdAndOrganizationId(taskId, orgId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        TaskComment comment = new TaskComment();
        comment.setTaskId(task.getId());
        comment.setMessage(message);
        comment.setAuthorId(userId);
        comment.setOrganizationId(orgId);

        return commentRepository.save(comment);
    }

    public List<TaskComment> listComments(
            String taskId,
            String orgId
    ) {
        // Task existence + org check
        taskRepository
                .findByIdAndOrganizationId(taskId, orgId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        return commentRepository
                .findByTaskIdAndOrganizationIdOrderByCreatedAtAsc(taskId, orgId);
    }
}
