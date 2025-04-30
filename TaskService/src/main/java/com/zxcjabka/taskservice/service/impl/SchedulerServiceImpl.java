package com.zxcjabka.taskservice.service.impl;

import com.zxcjabka.taskservice.persistence.repository.TaskRepository;
import com.zxcjabka.taskservice.service.SchedulerService;
import com.zxcjabka.taskservice.service.dto.TaskDTO;
import com.zxcjabka.taskservice.service.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {

    private final TaskRepository taskRepository;
    private final RabbitTemplate rabbitTemplate;
    private final TaskMapper taskMapper;


    @Override
    @Scheduled(fixedRate = 1800000)
    public void checkForExpiringTasks() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryTime = now.plusDays(1);

        List<TaskDTO> expiringTasks = taskRepository.findTaskEntitiesByDeadlineBetween(now, expiryTime)
                .stream()
                .map(taskMapper::toDto)
                .toList();
        expiringTasks.forEach(task -> {
            rabbitTemplate.convertAndSend(
                    "task.exchange",
                    "expiry.notification",
                    task
            );
            System.out.println("Отправлено уведомление для задачи: " + task.getTitle());
        });

    }
}
