package com.smartjira.smartjira.repository;

import com.smartjira.smartjira.dto.DetailsDevloperDto;
import com.smartjira.smartjira.dto.TaskType;
import com.smartjira.smartjira.model.Developer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeveloperRepository extends CrudRepository<Developer, Long> {

    Optional<Developer> findById(int id);

    @Query(value = "SELECT s.salary " +
            "FROM salary s " +
            "INNER JOIN developer d ON d.id = s.developer_id " +
            "WHERE d.id = :idDeveloper",
            nativeQuery = true)
    Integer getSalary(@Param("idDeveloper") int idDeveloper);


    @Query("SELECT t.name FROM Tasks t WHERE t.status = 'TODO' AND t.developer.id = :idDev")
    List<String> getAllTaskTodo(@Param("idDev") int idDev);

    @Query("SELECT new com.smartjira.smartjira.dto.TaskType(" +
            "SUM(CASE WHEN t.status = 'TODO' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN t.status = 'INPROGRESS' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN t.status = 'DONE' THEN 1 ELSE 0 END)) " +
            "FROM Tasks t " +
            "WHERE t.developer.id = :idDev")
    TaskType countAllTaskTypes(@Param("idDev") int idDev);

    @Query("SELECT new com.smartjira.smartjira.dto.DetailsDevloperDto(d.id, u.id, d.manager.id, u.name, u.email) " +
            "FROM Developer d INNER JOIN d.user u WHERE u.email = :email")
    DetailsDevloperDto getDetailsDeveloperByEmail(@Param("email") String email);






}
