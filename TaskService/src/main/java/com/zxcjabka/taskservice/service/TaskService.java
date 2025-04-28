package com.zxcjabka.taskservice.service;

import com.zxcjabka.taskservice.service.dto.TaskCreationForm;
import com.zxcjabka.taskservice.service.dto.TaskDTO;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getTasks(String userId);
    TaskDTO createTask(TaskCreationForm taskForm,String userId);

    String deleteTask(String userId, String taskTitle);
}
