package com.smartjira.smartjira.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeveloperDto {
    private String name;
    private String email;
}
