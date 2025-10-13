package com.smartjira.smartjira.repository;

import com.smartjira.smartjira.dto.DeveloperDto;
import com.smartjira.smartjira.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    @Query("""
        SELECT new com.smartjira.smartjira.dto.DeveloperDto(d.name, d.email)
        FROM Manager m
        JOIN m.projects p
        JOIN p.tasks t
        JOIN t.developer d
        WHERE m.id = :id
    """)
    List<DeveloperDto> findAllDevelopersByManagerId(@Param("id") int id);
}
