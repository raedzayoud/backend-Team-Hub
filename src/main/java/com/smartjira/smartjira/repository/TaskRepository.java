package com.smartjira.smartjira.repository;

import com.smartjira.smartjira.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
    Optional<Tasks> findById(long id);
}
