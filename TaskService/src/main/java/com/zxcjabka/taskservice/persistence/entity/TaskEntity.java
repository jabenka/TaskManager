package com.zxcjabka.taskservice.persistence.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@Table(name = "task")
public class TaskEntity {
    @Id
    Long id;

    Long userId;

    String title;

    String description;

    LocalDateTime deadline;

    Instant creationTime;

    Integer priority;

}