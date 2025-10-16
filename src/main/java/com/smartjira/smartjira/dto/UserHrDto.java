package com.smartjira.smartjira.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserHrDto {
    String name;
    String email;
    double salary;
    String password;
}
