package com.smartjira.smartjira.repository;

import com.smartjira.smartjira.model.Developer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DeveloperRepository extends CrudRepository<Developer, Long> {

    Optional<Developer> findById(int id);

    @Query(value = "SELECT s.salary " +
            "FROM salary s " +
            "INNER JOIN developer d ON d.id = s.developer_id " +
            "WHERE d.id = :idDeveloper",
            nativeQuery = true)
    Integer getSalary(@Param("idDeveloper") int idDeveloper);
}
