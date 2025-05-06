package com.zxcjabka.taskservice.exception.validation.validator;

import com.zxcjabka.taskservice.exception.validation.annotation.ValidTaskStatus;
import com.zxcjabka.taskservice.persistence.entity.TaskStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Set;
@Component
public class TaskStatusValidator implements ConstraintValidator<ValidTaskStatus, TaskStatus> {

    private static final Set<String> STATUSES = Set.of("DONE","IN_PROGRESS","TODO","ARCHIVED","UNDEFINED");

    @Override
    public boolean isValid(TaskStatus s, ConstraintValidatorContext constraintValidatorContext) {
        return STATUSES.contains(s);
    }
}
