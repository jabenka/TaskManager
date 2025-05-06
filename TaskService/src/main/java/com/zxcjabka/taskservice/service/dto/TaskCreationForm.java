package com.zxcjabka.taskservice.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zxcjabka.taskservice.exception.validation.annotation.ValidTaskStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreationForm {

    @JsonProperty("title")
    @Size(max = 50, message = "title size must be less than 50 characters")
    String title;
    @JsonProperty("description")
    @Size(max = 500, message = "Description must be less than 500 characters")
    String description;
    @JsonProperty("deadline")
    String deadline;
    @JsonProperty("priority")
    @Min(value = 1, message = "Priority must be at least 1")
    @Max(value = 5, message = "Priority must be at most 5")
    Integer priority;
    @JsonProperty("status")
    String status;

}
