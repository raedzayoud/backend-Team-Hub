package com.smartjira.smartjira.controller;

import com.smartjira.smartjira.dto.UserHrDto;
import com.smartjira.smartjira.model.Developer;
import com.smartjira.smartjira.model.User;
import com.smartjira.smartjira.service.HrService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class HrController {

    private final HrService hrService;
    @PostMapping("/createDeveloper")
    public ResponseEntity<Map<String, String>> createEmployee(@RequestBody UserHrDto dto) {
        Developer developer = hrService.createEmployee(dto);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Developer created successfully");
        response.put("developerId", String.valueOf(developer.getId()));
        return ResponseEntity.ok(response);
    }

}
