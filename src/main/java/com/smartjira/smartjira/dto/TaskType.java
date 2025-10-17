package com.smartjira.smartjira.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskType {
    private String TODO;
    private String INPROGRESS;
    private String DONE;
}
