package com.zxcjabka.taskservice.service.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskDTO {
    @JsonProperty("id")
    Long Id;
    @JsonProperty("userId")
    Long userId;
    @JsonProperty("title")
    String title;
    @JsonProperty("description")
    String description;
    @JsonProperty("deadline")
    LocalDateTime deadline;
    @JsonProperty("creationTime")
    Instant creationTime;
    @JsonProperty("priority")
    Integer priority;
}
