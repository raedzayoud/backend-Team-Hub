package com.smartjira.smartjira.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDeveloperDto {
    private int nameDeveloper;
    private int nameTask;
}
