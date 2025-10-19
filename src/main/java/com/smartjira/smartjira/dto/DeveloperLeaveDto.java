package com.smartjira.smartjira.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DeveloperLeaveDto {
    private String name;
    private String email;
    private int nbDays;
    private String leaveReason;
}
