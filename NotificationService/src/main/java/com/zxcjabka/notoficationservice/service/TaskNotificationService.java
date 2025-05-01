package com.zxcjabka.notoficationservice.service;

import com.zxcjabka.notoficationservice.service.dto.TaskDto;

public interface TaskNotificationService {

    void handleNotification(TaskDto task);
}
