package com.zxcjabka.notoficationservice.service.impl;

import com.zxcjabka.notoficationservice.misc.UserEmailFetcher;
import com.zxcjabka.notoficationservice.service.TaskNotificationService;
import com.zxcjabka.notoficationservice.service.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskNotificationServiceImpl implements TaskNotificationService {

    private final JavaMailSender javaMailSender;
    private final UserEmailFetcher userEmailFetcher;

    @Override
    @RabbitListener(queues = "task.expiry.notifications")
    public void handleNotification(TaskDto task) {
        String to = userEmailFetcher.fetchUserEmail(task.getUserId());

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("notoficationservice@gmail.com");
        message.setTo(to);

        message.setSubject(task.getTitle()+"expires in 1 day");
        message.setText(
                "Task: " + task.getTitle() + "\n" +
                        "Deadline: " + task.getDeadline() + "\n\n" +
                        "Please,close this task in time!"
        );
        javaMailSender.send(message);
        System.out.println("Письмо отправлено на: " + to);

    }
}
