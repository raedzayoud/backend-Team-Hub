package com.smartjira.smartjira.controller;

import com.smartjira.smartjira.config.JwtService;
import com.smartjira.smartjira.dto.DetailsDevloperDto;
import com.smartjira.smartjira.dto.TaskType;
import com.smartjira.smartjira.service.DeveloperService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/developer/")
public class DeveloperController {
    private final DeveloperService developerService;
    private  final JwtService jwtService;

    @GetMapping("{idDeveloper}")
    public ResponseEntity<Map<String, Integer>> getSalary(@PathVariable int idDeveloper) {
        Integer salary = developerService.getSarlary(idDeveloper);

        Map<String, Integer> response = new HashMap<>();
        response.put("salary", salary != null ? salary : 0); // avoid null values

        return ResponseEntity.ok(response);
    }

    @GetMapping("taskstodo/{idDeveloper}")
    public ResponseEntity<Map<String, List<String>>> getTasks(@PathVariable int idDeveloper) {
        Map<String, List<String>> response = new HashMap<>();
        List<String>l=developerService.getAllTaskTodo(idDeveloper);
        response.put("list",l);
        return ResponseEntity.ok(response);
    }

    @GetMapping("tasktype/{idDeveloper}")
    public ResponseEntity<Map<String, TaskType>> getTaskTypes(@PathVariable int idDeveloper) {
        Map<String, TaskType> response = new HashMap<>();
        TaskType t =developerService.countAllTaskTypes(idDeveloper);
        response.put("list",t);
        return ResponseEntity.ok(response);
    }

    @GetMapping("details")
    public ResponseEntity<Map<String, DetailsDevloperDto>>getDetails(@RequestHeader("Authorization")String token) {
        String jwt = token.substring(7);
        String email=jwtService.extractUsername(jwt);
        DetailsDevloperDto details = developerService.getDetailsDevloperByEmail(email);
        Map<String, DetailsDevloperDto> response = new HashMap<>();
        response.put("detailsDeveloper", details);
        return ResponseEntity.ok(response);
    }


}
