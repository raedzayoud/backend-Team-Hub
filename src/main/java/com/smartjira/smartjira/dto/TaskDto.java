package com.smartjira.smartjira.dto;


import com.smartjira.smartjira.enums.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskDto {
    private String id;
    private String name;
    private int idDeveloper;
    private Status status;
    private int idProject;
}
