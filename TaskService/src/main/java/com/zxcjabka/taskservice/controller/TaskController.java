package com.zxcjabka.taskservice.controller;

import com.zxcjabka.taskservice.service.TaskService;
import com.zxcjabka.taskservice.service.dto.TaskCreationForm;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getTasks(@RequestHeader("X-User-Id") String userId) {
        return ResponseEntity.ok(taskService.getTasks(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<?> addTask(
            @RequestHeader("X-User-Id") String userId,
            @RequestBody @Valid TaskCreationForm task) {
        return ResponseEntity.ok(taskService.createTask(task, userId));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTask(@RequestHeader("X-User-Id") String userId,
                                        @RequestParam(name = "name") String taskTitle) {
        return ResponseEntity.ok(taskService.deleteTask(userId, taskTitle));
    }

    @PatchMapping("/edit")
    public ResponseEntity<?> editTask(@RequestHeader("X-User-Id") String userId,
                                      @RequestParam("Id") String taskId,
                                      @RequestBody @Valid TaskCreationForm form) {
        return ResponseEntity.ok(taskService.editTask(taskId, userId, form));
    }

}
