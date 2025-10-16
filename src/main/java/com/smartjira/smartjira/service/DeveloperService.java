package com.smartjira.smartjira.service;

import com.smartjira.smartjira.model.Developer;
import com.smartjira.smartjira.repository.DeveloperRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeveloperService {
    private final DeveloperRepository developerRepository;

    public Developer findById(int id) {
        return developerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Developer id not found"));
    }

    public int getSarlary(int idDeveloper) {
        return developerRepository.getSalary(idDeveloper);
    }



}
