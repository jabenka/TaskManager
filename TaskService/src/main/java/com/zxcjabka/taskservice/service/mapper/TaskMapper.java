package com.zxcjabka.taskservice.service.mapper;

import com.zxcjabka.taskservice.persistence.entity.TaskEntity;
import com.zxcjabka.taskservice.service.dto.TaskCreationForm;
import com.zxcjabka.taskservice.service.dto.TaskDTO;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TaskMapper {


    public TaskDTO toDto(TaskEntity task) {
        return TaskDTO.builder()
                .Id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .priority(task.getPriority())
                .userId(task.getUserId())
                .deadline(task.getDeadline())
                .creationTime(task.getCreationTime())
                .build();
    }

    public TaskEntity toEntity(TaskDTO dto) {
        TaskEntity taskEntity=new TaskEntity();
        taskEntity.setId(dto.getId());
        taskEntity.setTitle(dto.getTitle());
        taskEntity.setDescription(dto.getDescription());
        taskEntity.setPriority(dto.getPriority());
        taskEntity.setUserId(dto.getUserId());
        taskEntity.setDeadline(dto.getDeadline());
        taskEntity.setCreationTime(dto.getCreationTime());
        return taskEntity;
    }

    public TaskEntity toEntity(TaskCreationForm form,String userId) {
        TaskEntity taskEntity=new TaskEntity();
        taskEntity.setUserId(Long.parseLong(userId));
        taskEntity.setDeadline(form.getDeadline());
        taskEntity.setDescription(form.getDescription());
        taskEntity.setPriority(form.getPriority());
        taskEntity.setTitle(form.getTitle());
        taskEntity.setCreationTime(Instant.now());
        return taskEntity;
    }


}
