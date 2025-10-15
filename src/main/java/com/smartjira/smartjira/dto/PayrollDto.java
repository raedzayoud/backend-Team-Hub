package com.smartjira.smartjira.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayrollDto {
    private double TotalSalary;
    private double AvregeSalary;
}
