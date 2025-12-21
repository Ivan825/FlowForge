package com.flowforge.task.repository;

import com.flowforge.task.entity.TaskActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskActivityRepository extends JpaRepository<TaskActivity, String> {

    List<TaskActivity> findByTaskIdOrderByCreatedAtAsc(String taskId);
}
