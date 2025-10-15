package com.smartjira.smartjira.repository;

import com.smartjira.smartjira.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Tasks, Long> {

    Optional<Tasks> findById(long id);

    @Query("""
        SELECT t 
        FROM Tasks t
        WHERE t.project.id = :projectId
    """)
    List<Tasks> findAllTaskByProjectId(@Param("projectId") int projectId);

    @Query("""
        SELECT t 
        FROM Tasks t
        WHERE t.developer.id = :developerId
    """)
    List<Tasks> findAllTaskByDeveloperId(@Param("developerId") int developerId);

}
