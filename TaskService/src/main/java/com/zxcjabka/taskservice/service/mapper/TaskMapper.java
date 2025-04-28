package com.zxcjabka.taskservice.service.mapper;

import com.zxcjabka.taskservice.persistence.entity.TaskEntity;
import com.zxcjabka.taskservice.persistence.entity.TaskStatus;
import com.zxcjabka.taskservice.service.dto.TaskCreationForm;
import com.zxcjabka.taskservice.service.dto.TaskDTO;
import com.zxcjabka.taskservice.service.misc.LocalDateTimeParser;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TaskMapper {

    private final LocalDateTimeParser localDateTimeParser;

    public TaskMapper(LocalDateTimeParser localDateTimeParser) {
        this.localDateTimeParser = localDateTimeParser;
    }

    public TaskDTO toDto(TaskEntity task) {
        return TaskDTO.builder()
                .Id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .priority(task.getPriority())
                .userId(task.getUserId())
                .deadline(task.getDeadline())
                .creationTime(task.getCreationTime())
                .status(task.getStatus())
                .build();
    }

    public TaskEntity toEntity(TaskDTO dto) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(dto.getId());
        taskEntity.setTitle(dto.getTitle());
        taskEntity.setDescription(dto.getDescription());
        taskEntity.setPriority(dto.getPriority());
        taskEntity.setUserId(dto.getUserId());
        taskEntity.setDeadline(dto.getDeadline());
        taskEntity.setCreationTime(dto.getCreationTime());
        return taskEntity;
    }

    public TaskEntity toEntity(TaskCreationForm form, String userId) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setUserId(Long.parseLong(userId));
        taskEntity.setDeadline(localDateTimeParser.parse(form.getDeadline()));
        taskEntity.setDescription(form.getDescription());
        taskEntity.setPriority(form.getPriority());
        taskEntity.setTitle(form.getTitle());
        taskEntity.setCreationTime(Instant.now());
        String status = form.getStatus() == null ? String.valueOf(TaskStatus.UNDEFINED) : form.getStatus();
        taskEntity.setStatus(TaskStatus.valueOf(status));
        return taskEntity;
    }

    public TaskEntity copyTaskEntity(TaskEntity existingTask, TaskEntity newTask) {
        if (newTask.getTitle() != null) {
            existingTask.setTitle(newTask.getTitle());
        }
        if (newTask.getDescription() != null) {
            existingTask.setDescription(newTask.getDescription());
        }
        if (newTask.getPriority() != null) {
            existingTask.setPriority(newTask.getPriority());
        }
        if (newTask.getStatus() != TaskStatus.UNDEFINED) {
            existingTask.setStatus(newTask.getStatus());
        }
        if (newTask.getDeadline() != null) {
            existingTask.setDeadline(newTask.getDeadline());
        }


        return existingTask;
    }

}
