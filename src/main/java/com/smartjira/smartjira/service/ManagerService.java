package com.smartjira.smartjira.service;

import com.smartjira.smartjira.dto.DeveloperDto;
import com.smartjira.smartjira.dto.DeveloperLeaveDto;
import com.smartjira.smartjira.dto.ProjectDto;
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
}
