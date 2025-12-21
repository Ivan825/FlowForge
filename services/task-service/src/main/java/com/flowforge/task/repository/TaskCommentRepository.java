package com.flowforge.task.repository;

import com.flowforge.task.entity.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskCommentRepository extends JpaRepository<TaskComment, String> {

    List<TaskComment> findByTaskIdAndOrganizationIdOrderByCreatedAtAsc(
            String taskId,
            String organizationId
    );
}
