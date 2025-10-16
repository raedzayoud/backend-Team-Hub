package com.smartjira.smartjira.controller;

import com.smartjira.smartjira.dto.DeveloperDto;
import com.smartjira.smartjira.dto.UserHrDto;
import com.smartjira.smartjira.model.Developer;
import com.smartjira.smartjira.model.User;
import com.smartjira.smartjira.service.HrService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/hr")
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

    @GetMapping("/developers")
    public  ResponseEntity<Map<String, List<DeveloperDto>>>getAllDevelopers() {
        HashMap<String, List<DeveloperDto>> response = new HashMap<>();
        List<DeveloperDto>list=hrService.getAllDevelopers();
        response.put("developers", list);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/developers/{username}")
    public ResponseEntity<Map<String, List<DeveloperDto>>>searchDevelopers(@PathVariable("username") String username) {
        HashMap<String, List<DeveloperDto>> response = new HashMap<>();
        List<DeveloperDto>list=hrService.SearchDeveloperInHrDashboard(username);
        response.put("developers", list);
        return ResponseEntity.ok(response);
    }


}
