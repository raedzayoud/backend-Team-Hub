package com.smartjira.smartjira.controller;

import com.smartjira.smartjira.dto.CreateProjectDto;
import com.smartjira.smartjira.model.Manager;
import com.smartjira.smartjira.model.Project;
import com.smartjira.smartjira.model.Tasks;
import com.smartjira.smartjira.service.ManagerService;
import com.smartjira.smartjira.service.ProjectService;
import com.smartjira.smartjira.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/project/")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

    private final ProjectService projectService;
    private final ManagerService  managerService;

    @GetMapping
    public ResponseEntity<Map<String,List<Project>>> getAllProjects() {
       List<Project> projects=  projectService.getAllProjects();
       Map<String,List<Project>> map=new HashMap<>();
       map.put("projects",projects);
       return ResponseEntity.ok(map);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Project>> getProjectById(@PathVariable int id) {
        Project project=  projectService.getProjectById(id);
        Map<String,Project> map=new HashMap<>();
        map.put("project",project);
        return ResponseEntity.ok(map);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createProject(@RequestBody CreateProjectDto projectDto) {
        Map<String, String> map = new HashMap<>();

        Project project = new Project();
        project.setName(projectDto.getName());

        // Fetch manager by ID
        Manager manager = managerService.getManagerById(projectDto.getId_manager());
        project.setManager(manager);

        projectService.addProject(project);

        map.put("success", "Project created successfully");
        return ResponseEntity.ok(map);
    }







}
