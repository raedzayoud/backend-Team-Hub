package com.smartjira.smartjira.service;

import com.smartjira.smartjira.dto.DetailsDevloperDto;
import com.smartjira.smartjira.dto.TaskType;
import com.smartjira.smartjira.model.Developer;
import com.smartjira.smartjira.repository.DeveloperRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeveloperService {
    private final DeveloperRepository developerRepository;

    public Developer findById(int id) {
        return developerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Developer id not found"));
    }

    public Integer getSarlary(int idDeveloper) {
        return developerRepository.getSalary(idDeveloper);
    }

    public List<String>getAllTaskTodo(int idDeveloper) {
        return developerRepository.getAllTaskTodo(idDeveloper);
    }

    public TaskType countAllTaskTypes(int idDeveloper) {
        return developerRepository.countAllTaskTypes(idDeveloper);
    }

    public DetailsDevloperDto getDetailsDevloperByEmail(String email) {
        return developerRepository.getDetailsDeveloperByEmail(email);
    }


}
