package com.smartjira.smartjira.repository;

import com.smartjira.smartjira.dto.DeveloperDto;
import com.smartjira.smartjira.dto.UserHrDto;
import com.smartjira.smartjira.model.Developer;
import com.smartjira.smartjira.model.Hr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HrRepository extends JpaRepository<Hr,Long> {
    @Query("SELECT new com.smartjira.smartjira.dto.DeveloperDto(u.name, u.email) " +
            "FROM User u " +
            "JOIN Developer d ON u.id = d.user.id " +
            "WHERE u.roles = 'DEVELOPER'")
    List<DeveloperDto> getAllDeveloperinHrDashboad();

    @Query("SELECT new com.smartjira.smartjira.dto.DeveloperDto(u.name, u.email) " +
            "FROM User u " +
            "JOIN Developer d ON u.id = d.user.id " +
            "WHERE u.roles = 'DEVELOPER' AND u.name LIKE %:user_name%")
    List<DeveloperDto> searchDeveloperInHrDashboard(@Param("user_name") String username);




}
