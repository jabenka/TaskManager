package com.zxcjabka.taskservice.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    String title;
    @JsonProperty("description")
    String description;
    @JsonProperty("deadline")
    String deadline;
    @JsonProperty("priority")
    Integer priority;
    @JsonProperty("status")
    String status;

}
