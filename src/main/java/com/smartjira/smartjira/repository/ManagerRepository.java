package com.smartjira.smartjira.repository;

import com.smartjira.smartjira.dto.DeveloperDto;
import com.smartjira.smartjira.dto.DeveloperLeaveDto;
import com.smartjira.smartjira.dto.ProjectDto;
import com.smartjira.smartjira.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    @Query("""
        SELECT new com.smartjira.smartjira.dto.DeveloperDto(d.user.name, d.user.email)
        FROM Manager m
        JOIN m.projects p
        JOIN p.tasks t
        JOIN t.developer d
        WHERE m.id = :id    
    """)
    List<DeveloperDto> findAllDevelopersByManagerId(@Param("id") int id);

    @Query("""
        SELECT new com.smartjira.smartjira.dto.ProjectDto(p.name)
        FROM Manager m
        JOIN m.projects p
        WHERE m.id = :idManager
    """)
    List<ProjectDto> findAllProjectsByManagerId(@Param("idManager") int idManager);

    @Query("""
    SELECT new com.smartjira.smartjira.dto.DeveloperLeaveDto(
        d.user.name, 
        d.user.email, 
        l.nbDays, 
        l.reason
    )
    FROM Manager m
    JOIN m.projects p
    JOIN p.tasks t
    JOIN t.developer d
    JOIN d.leaveReasons l
    WHERE m.id = :idManager
    AND l.status = com.smartjira.smartjira.enums.StatusLeave.Pending
    """)
    List<DeveloperLeaveDto> findAllLeaveByManagerId(@Param("idManager") int idManager);



}
