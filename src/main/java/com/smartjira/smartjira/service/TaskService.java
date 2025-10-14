package com.smartjira.smartjira.service;

import com.smartjira.smartjira.model.Project;
import com.smartjira.smartjira.model.Tasks;
import com.smartjira.smartjira.repository.ProjectRepository;
import com.smartjira.smartjira.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Tasks addTask(Tasks task) {
        return taskRepository.save(task);
    }

    public List<Tasks> getAllTasks() {
        return taskRepository.findAll();
    }

    public Tasks getTaskById(int id) {
        return taskRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Project with id " + id + " not found")
        );
    }
    public List<Tasks> getTasksByProjectId(int projectId) {
        return taskRepository.findAllTaskByProjectId(projectId);
    }

}
