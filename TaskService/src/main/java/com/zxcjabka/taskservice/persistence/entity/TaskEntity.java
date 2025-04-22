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
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    Long userId;

    String title;

    String description;

    LocalDateTime deadline;

    Instant creationTime;

    Integer priority;

}