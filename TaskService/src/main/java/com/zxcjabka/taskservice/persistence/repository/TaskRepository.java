package com.zxcjabka.taskservice.persistence.repository;

import com.zxcjabka.taskservice.persistence.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findAllByUserId(Long userId);
}
