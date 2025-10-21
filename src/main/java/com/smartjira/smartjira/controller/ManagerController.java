package com.smartjira.smartjira.controller;

import com.smartjira.smartjira.config.JwtService;
import com.smartjira.smartjira.dto.*;
import com.smartjira.smartjira.service.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/manager/")
@CrossOrigin(origins = "http://localhost:4200")
public class ManagerController {

    private final ManagerService managerService;
    private final JwtService jwtService;

    @GetMapping("developers/{id}")
    public ResponseEntity<Map<String, List<DeveloperDto>>> getDevelopersByManager(@PathVariable int id) {
        List<DeveloperDto> developers = managerService.getDevelopersByManagerId(id);
        Map<String, List<DeveloperDto>> response = new HashMap<>();
        response.put("developers", developers);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{idManager}")
    public ResponseEntity<Map<String, List<ProjectDto>>> getProjectsByManagerId(@PathVariable int idManager) {
        List<ProjectDto> projects = managerService.getProjectsByManagerId(idManager);
        Map<String, List<ProjectDto>> response = new HashMap<>();
        response.put("projects", projects);
        return ResponseEntity.ok(response);
    }

    @GetMapping("pending-leaves/{idManager}")
    public ResponseEntity<Map<String,List<DeveloperLeaveDto>>> getPendingLeaves(@PathVariable int idManager){
        List<DeveloperLeaveDto> leaves = managerService.getAllPendingLeavesByManagerId(idManager);
        HashMap m=new HashMap();
        m.put("pendingleaves", leaves);
        return ResponseEntity.ok(m);
    }

    @GetMapping("/dasboard/{idManger}")
    public ResponseEntity<Map<String,List<TaskDeveloperDto>>> getCompletedTaskDev(@PathVariable int idManger) {
        List<TaskDeveloperDto>list=managerService.getAllCompletedTaskDeveloperByManagerId(idManger);
        HashMap m=new HashMap();
        m.put("completedtaskdevelopers", list);
        return ResponseEntity.ok(m);
    }

    @GetMapping("/counts/{managerId}")
    public ResponseEntity<Map<String,TaskType>> getTaskCounts(@PathVariable int managerId) {
        TaskType counts = managerService.getTaskCountsByManager(managerId);
        HashMap m=new HashMap();
        m.put("counts", counts);
        return ResponseEntity.ok(m);
    }


    @GetMapping("details")
    public ResponseEntity<Map<String, DetailsManagerDto>>getDetails(@RequestHeader("Authorization")String token) {
        String jwt = token.substring(7);
        String email=jwtService.extractUsername(jwt);
        DetailsManagerDto details = managerService.getDetailsManagerByEmail(email);
        Map<String, DetailsManagerDto> response = new HashMap<>();
        response.put("detailsManager", details);
        return ResponseEntity.ok(response);
    }




}
