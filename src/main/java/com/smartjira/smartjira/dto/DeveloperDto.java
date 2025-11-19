package com.smartjira.smartjira.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
public class DeveloperDto {
    private Long id;
    private String name;
    private String email;

    public DeveloperDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public DeveloperDto( String name, String email) {
        this.name = name;
        this.email = email;
    }

}
