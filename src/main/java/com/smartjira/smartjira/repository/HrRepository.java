package com.smartjira.smartjira.repository;

import com.smartjira.smartjira.dto.*;
import com.smartjira.smartjira.model.Developer;
import com.smartjira.smartjira.model.Hr;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HrRepository extends JpaRepository<Hr,Long> {
    @Query(value = "SELECT new com.smartjira.smartjira.dto.DeveloperDto(u.id, u.name, u.email) " +
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
    @Query("UPDATE LeaveReason l SET l.status = 'APPROVED' WHERE l.status = 'PENDING' AND l.developer.id = :idDev")
    void updatePendingToApprove(@Param("idDev") int idDev);


    @Transactional
    @Modifying
    @Query("UPDATE LeaveReason l SET l.status = 'REJECTED' WHERE l.status = 'PENDING' AND l.developer.id = :idDev")
    void updatePendingToRejected(@Param("idDev") int idDev);

    @Transactional
    @Modifying
    @Query("""
    UPDATE Developer d
    SET d.manager.id = :managerId
    WHERE d.user.id = :userId""")
    void affectDeveloperManagerId(@Param("managerId") long managerId,
                                  @Param("userId") long userId);

    @Query("""
    SELECT new com.smartjira.smartjira.dto.ManagerDto(
        m.id,
        m.user.name
    )
    FROM Manager m
    WHERE m.user.roles = com.smartjira.smartjira.enums.Role.MANAGER
""")
    List<ManagerDto> getAllManagers();

    @Query("""
    SELECT new com.smartjira.smartjira.dto.UserWithoutManagerDto(
        u.id,
        u.name
    )
    FROM Developer d
    JOIN d.user u
    WHERE d.manager IS NULL
""")
    List<UserWithoutManagerDto> getAllDeveloperWithoutManagers();



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

    @Query("SELECT COUNT(d) FROM Developer d")
    int countDeveloper();
    @Query("select count(l)from  LeaveReason l where l.status='PENDING'")
    int countPendingLeave();

    @Query("select count(l)from  LeaveReason l where l.status='APPROVED'")
    int countApprovedLeave();

    @Query("select sum(s.salary) from Salary s")
    Long countSumSalaryDeveloper();

    @Query("select avg(s.salary) from Salary s")
    Double averageSalaryDeveloper();








}
