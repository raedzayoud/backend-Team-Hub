package com.smartjira.smartjira.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeveloperLeaveDto {
    private String name;
    private String email;
    private int nbDays;
    private String leaveReason;
}
