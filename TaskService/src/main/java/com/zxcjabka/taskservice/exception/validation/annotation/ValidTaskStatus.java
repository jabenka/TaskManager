package com.zxcjabka.taskservice.exception.validation.annotation;

import com.zxcjabka.taskservice.exception.validation.validator.TaskStatusValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = TaskStatusValidator.class)
public @interface ValidTaskStatus {
    String message() default "Task status is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
