package com.smartjira.smartjira.service;

import com.smartjira.smartjira.dto.PayrollDto;
import com.smartjira.smartjira.repository.SalaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalaryService {

    private final SalaryRepository salaryRepository;

    public PayrollDto getTotalAndAverageSalary() {
        return salaryRepository.getTotalAvgSalary();
    }
}