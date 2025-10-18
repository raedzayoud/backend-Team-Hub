package com.smartjira.smartjira.controller;

import com.smartjira.smartjira.dto.TaskType;
import com.smartjira.smartjira.service.DeveloperService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/developer/")
public class DeveloperController {
    private final DeveloperService developerService;

    @GetMapping("/{idDeveloper}")
    public ResponseEntity<Map<String, Integer>> getSalary(@PathVariable int idDeveloper) {
        Integer salary = developerService.getSarlary(idDeveloper);

        Map<String, Integer> response = new HashMap<>();
        response.put("salary", salary != null ? salary : 0); // avoid null values

        return ResponseEntity.ok(response);
    }

    @GetMapping("/taskstodo/{idDeveloper}")
    public ResponseEntity<Map<String, List<String>>> getTasks(@PathVariable int idDeveloper) {
        Map<String, List<String>> response = new HashMap<>();
        List<String>l=developerService.getAllTaskTodo(idDeveloper);
        response.put("list",l);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tasktype/{idDeveloper}")
    public ResponseEntity<Map<String, TaskType>> getTaskTypes(@PathVariable int idDeveloper) {
        Map<String, TaskType> response = new HashMap<>();
        TaskType t =developerService.countAllTaskTypes(idDeveloper);
        response.put("list",t);
        return ResponseEntity.ok(response);
    }


}
