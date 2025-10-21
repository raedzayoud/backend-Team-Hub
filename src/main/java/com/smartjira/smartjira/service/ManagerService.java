package com.smartjira.smartjira.service;

import com.smartjira.smartjira.dto.*;
import com.smartjira.smartjira.model.Manager;
import com.smartjira.smartjira.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    public List<DeveloperDto> getDevelopersByManagerId(int managerId) {
        return managerRepository.findAllDevelopersByManagerId(managerId);
    }
    public List<ProjectDto> getProjectsByManagerId(int managerId) {
        return managerRepository.findAllProjectsByManagerId(managerId);
    }

    public List<DeveloperLeaveDto> getAllPendingLeavesByManagerId(int managerId){
        return managerRepository.findAllLeaveByManagerId(managerId);
    }

    public List<TaskDeveloperDto> getAllCompletedTaskDeveloperByManagerId(int managerId){
       return managerRepository.getAllCompletedTaskDeveloper(managerId);
    }

    public TaskType getTaskCountsByManager(int managerId) {
        return managerRepository.countAllTaskTypes(managerId);
    }

    public Manager getManagerById(int id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manager not found with ID: " + id));
    }

    public DetailsManagerDto getDetailsManagerByEmail(String email) {
        return managerRepository.getDetailsManagerByEmail(email);
    }



}
