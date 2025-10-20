package com.smartjira.smartjira.controller;

import com.smartjira.smartjira.dto.PayrollDto;
import com.smartjira.smartjira.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/salary/")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryService salaryService;

    @GetMapping("total-average")
    public ResponseEntity<Map<String,PayrollDto>>  getTotalAndAverageSalary() {
        HashMap<String,PayrollDto> map = new HashMap<>();
        map.put("totalavrege",salaryService.getTotalAndAverageSalary());
        return ResponseEntity.ok(map);
    }
}
