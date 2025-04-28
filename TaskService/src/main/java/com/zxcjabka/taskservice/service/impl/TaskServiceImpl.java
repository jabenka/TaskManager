package com.zxcjabka.taskservice.service.impl;

import com.zxcjabka.taskservice.persistence.entity.TaskEntity;
import com.zxcjabka.taskservice.persistence.repository.TaskRepository;
import com.zxcjabka.taskservice.service.TaskService;
import com.zxcjabka.taskservice.service.dto.TaskCreationForm;
import com.zxcjabka.taskservice.service.dto.TaskDTO;
import com.zxcjabka.taskservice.service.mapper.TaskMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<TaskDTO> getTasks(String userId) {
        List<TaskEntity> optionalTask = taskRepository.findAllByUserId(Long.valueOf(userId));
        return optionalTask.stream().map(taskMapper::toDto).toList();
    }

    @Override
    @Transactional
    public TaskDTO createTask(TaskCreationForm taskForm, String userId) {
        TaskEntity newTask = taskMapper.toEntity(taskForm, userId);
        newTask = taskRepository.saveAndFlush(newTask);
        return taskMapper.toDto(newTask);
    }

    @Override
    public String deleteTask(String userId, String taskTitle) {
        Optional<TaskEntity> taskEntity = taskRepository.findTaskEntityByTitle(taskTitle);
        TaskEntity task = taskEntity.stream().filter(t -> t.getUserId().equals(Long.parseLong(userId))).findFirst().orElse(null);
        if (task != null) {
            taskRepository.delete(task);
        } else {
            throw new IllegalArgumentException("Task title and userId does not match");
        }
        return String.format("Task with title %s has been deleted", taskTitle);
    }
}
