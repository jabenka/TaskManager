package com.zxcjabka.taskservice.persistence.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@ToString
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    Long userId;
    @Column(nullable = false,unique = true)
    String title;

    String description;

    LocalDateTime deadline;

    Instant creationTime;

    Integer priority;

    @Enumerated(EnumType.STRING)
    TaskStatus status;

    public TaskEntity(TaskEntity newTask) {
        this.id = newTask.id;
        this.userId = newTask.userId;
        this.title = newTask.title;
        this.description = newTask.description;
        this.deadline = newTask.deadline;
        this.creationTime = newTask.creationTime;
        this.priority = newTask.priority;
        this.status = newTask.status;
    }
}