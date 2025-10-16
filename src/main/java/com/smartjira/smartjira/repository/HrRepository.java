package com.smartjira.smartjira.repository;

import com.smartjira.smartjira.dto.DeveloperDto;
import com.smartjira.smartjira.dto.LeaveUserDto;
import com.smartjira.smartjira.dto.UserHrDto;
import com.smartjira.smartjira.model.Developer;
import com.smartjira.smartjira.model.Hr;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HrRepository extends JpaRepository<Hr,Long> {
    @Query(value = "SELECT new com.smartjira.smartjira.dto.DeveloperDto(u.name, u.email) " +
            "FROM User u " +
            "JOIN Developer d ON u.id = d.user.id " +
            "WHERE u.roles = 'DEVELOPER'")
    List<DeveloperDto> getAllDeveloperinHrDashboad();

    @Query(value = "SELECT new com.smartjira.smartjira.dto.DeveloperDto(u.name, u.email) " +
            "FROM User u " +
            "JOIN Developer d ON u.id = d.user.id " +
            "WHERE u.roles = 'DEVELOPER' AND u.name LIKE %:user_name%")
    List<DeveloperDto> searchDeveloperInHrDashboard(@Param("user_name") String username);
    @Query("SELECT new com.smartjira.smartjira.dto.LeaveUserDto(l.reason, l.nbDays, u.name) " +
            "FROM LeaveReason l " +
            "JOIN l.developer d " +
            "JOIN d.user u " +
            "WHERE l.status = 'PENDING'")
    List<LeaveUserDto> getPendingLeave();
    @Transactional
    @Modifying
    @Query("UPDATE LeaveReason l SET l.status = 'APPROVED' WHERE l.status = 'PENDING'")
   void updatePendingToApprove();


    @Transactional
    @Modifying
    @Query("UPDATE LeaveReason l SET l.status = 'REJECTED' WHERE l.status = 'PENDING'")


    void updatePendingToRejected();

    @Query("SELECT new com.smartjira.smartjira.dto.LeaveUserDto(l.reason, l.nbDays, u.name) " +
            "FROM LeaveReason l " +
            "JOIN l.developer d " +
            "JOIN d.user u " +
            "WHERE l.status = 'APPROVED'")
    List<LeaveUserDto> getApprovedLeave();

    @Query("SELECT new com.smartjira.smartjira.dto.LeaveUserDto(l.reason, l.nbDays, u.name) " +
            "FROM LeaveReason l " +
            "JOIN l.developer d " +
            "JOIN d.user u " +
            "WHERE l.status = 'REJECTED'")
    List<LeaveUserDto> getRejectedLeave();




}
