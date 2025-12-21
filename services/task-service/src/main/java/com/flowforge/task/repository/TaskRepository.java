package com.flowforge.task.repository;

import com.flowforge.task.entity.Task;
import com.flowforge.task.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, String> {

    List<Task> findByOrganizationId(String organizationId);

    List<Task> findByOrganizationIdAndStatus(
            String organizationId,
            TaskStatus status
    );

    Optional<Task> findByIdAndOrganizationId(
            String id,
            String organizationId
    );

    List<Task> findByAssignedTo(String assignedTo);
}
