package com.smartjira.smartjira.repository;

import com.smartjira.smartjira.model.Developer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeveloperRepository extends CrudRepository<Developer, Long> {
    Optional<Developer> findById(int id);
}
