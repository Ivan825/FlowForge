package com.flowforge.task.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "task_comments")
public class TaskComment {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String taskId;

    @Column(nullable = false, length = 1000)
    private String message;

    @Column(nullable = false)
    private String authorId;

    @Column(nullable = false)
    private String organizationId;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    // ---- getters & setters ----

    public String getId() { return id; }

    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getAuthorId() { return authorId; }
    public void setAuthorId(String authorId) { this.authorId = authorId; }

    public String getOrganizationId() { return organizationId; }
    public void setOrganizationId(String organizationId) { this.organizationId = organizationId; }

    public Instant getCreatedAt() { return createdAt; }
}
