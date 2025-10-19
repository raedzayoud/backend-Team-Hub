package com.smartjira.smartjira.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LeaveDto {
    private String reason;
    private int nbDays;
    private int developer_id;
}
