package com.prajna.taskmanager.repository;

import com.prajna.taskmanager.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}