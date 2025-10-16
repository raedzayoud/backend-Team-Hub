package com.smartjira.smartjira.dto;

import com.smartjira.smartjira.model.Developer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaveUserDto {
    private String reason;
    private int  nbDays ;
    private String userName;
}
