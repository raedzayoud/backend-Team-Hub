package com.smartjira.smartjira.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeaveDto {
    private String reason;
    private int nbDays;
    private int developer_id;
}
