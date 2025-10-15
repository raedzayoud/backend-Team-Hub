package com.smartjira.smartjira.service;

import com.smartjira.smartjira.dto.UserHrDto;
import com.smartjira.smartjira.enums.Role;
import com.smartjira.smartjira.model.Developer;
import com.smartjira.smartjira.model.User;
import com.smartjira.smartjira.repository.DeveloperRepository;
import com.smartjira.smartjira.repository.HrRepository;
import com.smartjira.smartjira.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class HrService {
    private final UserRepository userRepository;
    private final DeveloperRepository developerRepository;

    public Developer createEmployee(UserHrDto dto) {
        // Create and save the user
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRoles(Role.DEVELOPER);

        User savedUser = userRepository.save(user);

        // Create developer linked to the user
        Developer developer = new Developer();
        developer.setUser(savedUser);
        developer.setManager(null);

        return developerRepository.save(developer);
    }
}
