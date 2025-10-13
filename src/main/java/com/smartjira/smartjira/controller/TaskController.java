package com.smartjira.smartjira.controller;

import com.smartjira.smartjira.dto.TaskDto;
import com.smartjira.smartjira.model.Tasks;
import com.smartjira.smartjira.repository.DeveloperRepository;
import com.smartjira.smartjira.service.DeveloperService;
import com.smartjira.smartjira.service.ProjectService;
import com.smartjira.smartjira.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/task/")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;
    private final DeveloperService developerService;
    @GetMapping
    public ResponseEntity<Map<String, List<Tasks>>> getAllTasks() {
        List<Tasks> tasks = taskService.getAllTasks();
        Map<String, List<Tasks>> map = new HashMap<>();
        map.put("tasks", tasks);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Tasks>> getTaskById(@PathVariable int id) {
        Tasks task = taskService.getTaskById(id);
        Map<String, Tasks> map = new HashMap<>();
        map.put("task", task);
        return ResponseEntity.ok(map);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createTask(@RequestBody TaskDto taskDto) {
        Tasks task = Tasks.builder()
                .name(taskDto.getName())
                .status(taskDto.getStatus())
                .developer(developerService.findById(taskDto.getIdDeveloper()))
                .project(projectService.getProjectById(taskDto.getIdProject()))
                .build();

        taskService.addTask(task);

        Map<String, String> response = new HashMap<>();
        response.put("success", "Task created successfully");
        return ResponseEntity.ok(response);
    }


}
