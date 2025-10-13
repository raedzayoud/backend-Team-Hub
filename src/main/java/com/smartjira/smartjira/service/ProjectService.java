package com.smartjira.smartjira.service;

import com.smartjira.smartjira.model.Project;
import com.smartjira.smartjira.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(int id) {
        return projectRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Project with id " + id + " not found")
        );
    }


}
