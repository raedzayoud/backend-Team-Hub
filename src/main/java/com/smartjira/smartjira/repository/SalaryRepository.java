package com.smartjira.smartjira.repository;

import com.smartjira.smartjira.dto.PayrollDto;
import com.smartjira.smartjira.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SalaryRepository extends JpaRepository<Salary, Long> {

    @Query("SELECT new com.smartjira.smartjira.dto.PayrollDto(SUM(s.salary), AVG(s.salary)) FROM Salary s")
    PayrollDto getTotalAvgSalary();
}
