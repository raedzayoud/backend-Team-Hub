package com.smartjira.smartjira.dto;


import com.smartjira.smartjira.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private String id;
    private String name;
    private int idDeveloper;
    private Status status;
    private int idProject;
}
