package com.smartjira.smartjira.controller;

import com.smartjira.smartjira.dto.*;
import com.smartjira.smartjira.model.Developer;
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

    // ----------------- Developer Management -----------------


    @GetMapping("/developers/without-manager")
    public List<UserWithoutManagerDto> getDevelopersWithoutManager() {
        return hrService.getAllDevelopersWithoutManagers();
    }


    @PutMapping("/affecteManager/{userId}/manager/{managerId}")
    public ResponseEntity<Map<String,String>> affectManager(
            @PathVariable long userId,
            @PathVariable long managerId) {

        hrService.affectDeveloperToManager(managerId, userId);
        Map<String,String> map = new HashMap<>();
        map.put("status", "Manager assigned successfully");
        return ResponseEntity.ok(map);
    }

    @PostMapping("/createDeveloper")
    public ResponseEntity<Map<String, String>> createEmployee(@RequestBody UserHrDto dto) {
        Developer developer = hrService.createEmployee(dto);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Developer created successfully");
        response.put("developerId", String.valueOf(developer.getId()));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/developers")
    public ResponseEntity<Map<String, List<DeveloperDto>>> getAllDevelopers() {
        HashMap<String, List<DeveloperDto>> response = new HashMap<>();
        List<DeveloperDto> list = hrService.getAllDevelopers();
        response.put("developers", list);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/developers/{username}")
    public ResponseEntity<Map<String, List<DeveloperDto>>> searchDevelopers(@PathVariable("username") String username) {
        HashMap<String, List<DeveloperDto>> response = new HashMap<>();
        List<DeveloperDto> list = hrService.SearchDeveloperInHrDashboard(username);
        response.put("developers", list);
        return ResponseEntity.ok(response);
    }

   // Manages
   @GetMapping("/Managers")
   public ResponseEntity<Map<String,List<ManagerDto>>> getManagers() {
        Map<String,List<ManagerDto>> response = new HashMap<>();
        response.put("managers", hrService.getAllManagers());
       return ResponseEntity.ok(response);
   }

    // ----------------- Leave Requests -----------------

    @GetMapping("/leaves/pending")
    public ResponseEntity<Map<String,List<LeaveUserDto>>> getPendingLeaves() {
        HashMap<String, List<LeaveUserDto>> response = new HashMap<>();
        List<LeaveUserDto> list = hrService.getPendingLeaves();
        response.put("leaves", list);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/leaves/approved")
    public ResponseEntity<Map<String,List<LeaveUserDto>>> getApprovedLeaves() {
        HashMap<String, List<LeaveUserDto>> response = new HashMap<>();
        List<LeaveUserDto> list = hrService.getApprovedLeaves();
        response.put("approved", list);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/leaves/rejected")
    public ResponseEntity<Map<String,List<LeaveUserDto>>> getRejectedLeaves() {
        HashMap<String, List<LeaveUserDto>> response = new HashMap<>();
        List<LeaveUserDto> list = hrService.getRejectedLeaves();
        response.put("rejected", list);
        return ResponseEntity.ok(response);
    }

    // ----------------- Approve / Reject Leaves -----------------

    @PutMapping("/leaves/approve/{idDev}")
    public ResponseEntity<Map<String, String>> approveAllPendingLeaves(@PathVariable int idDev) {
        hrService.approveAllPendingLeaves(idDev);
        Map<String, String> response = new HashMap<>();
        response.put("message", "All pending leaves for developer " + idDev + " have been approved.");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/leaves/reject/{idDev}")
    public ResponseEntity<Map<String, String>> rejectAllPendingLeaves(@PathVariable int idDev) {
        hrService.rejectAllPendingLeaves(idDev);
        Map<String, String> response = new HashMap<>();
        response.put("message", "All pending leaves for developer " + idDev + " have been rejected.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/countApproved")
    public ResponseEntity<Map<String, Integer>> countApprovedLeaves() {
        HashMap<String, Integer> response = new HashMap<>();
        response.put("countApprovedLeaves", hrService.countApprovedLeave());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/countPending")
    public ResponseEntity<Map<String, Integer>> countPending() {
        HashMap<String, Integer> response = new HashMap<>();
        response.put("countPending", hrService.countPendingLeave());
        return ResponseEntity.ok(response);
    }



}
