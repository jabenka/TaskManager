package com.zxcjabka.notoficationservice.service.dto;

import lombok.Getter;

import java.time.Instant;
import java.time.LocalDateTime;
@Getter
public class TaskDto {
    Long id;
    Long userId;
    String title;
    String description;
    LocalDateTime deadline;
    Instant creationTime;
    Integer priority;
    TaskStatus status;
}
