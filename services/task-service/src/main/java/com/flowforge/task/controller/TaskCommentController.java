package com.flowforge.task.controller;

import com.flowforge.task.entity.TaskComment;
import com.flowforge.task.service.TaskCommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks/{taskId}/comments")
public class TaskCommentController {

    private final TaskCommentService service;

    public TaskCommentController(TaskCommentService service) {
        this.service = service;
    }

    @PostMapping
    public TaskComment addComment(
            @PathVariable String taskId,
            @RequestBody Map<String, String> body,
            HttpServletRequest request
    ) {
        return service.addComment(
                taskId,
                body.get("message"),
                request.getHeader("X-Org-Id"),
                request.getHeader("X-User-Id")
        );
    }

    @GetMapping
    public List<TaskComment> listComments(
            @PathVariable String taskId,
            HttpServletRequest request
    ) {
        return service.listComments(
                taskId,
                request.getHeader("X-Org-Id")
        );
    }
}
