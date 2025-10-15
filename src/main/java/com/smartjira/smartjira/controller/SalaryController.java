package com.smartjira.smartjira.controller;

import com.smartjira.smartjira.dto.PayrollDto;
import com.smartjira.smartjira.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/salary")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryService salaryService;

    @GetMapping("/total-average")
    public PayrollDto getTotalAndAverageSalary() {
        return salaryService.getTotalAndAverageSalary();
    }
}
