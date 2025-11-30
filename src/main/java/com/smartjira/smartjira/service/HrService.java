package com.smartjira.smartjira.service;

import com.smartjira.smartjira.dto.*;
import com.smartjira.smartjira.enums.Role;
import com.smartjira.smartjira.model.Developer;
import com.smartjira.smartjira.model.Salary;
import com.smartjira.smartjira.model.User;
import com.smartjira.smartjira.repository.DeveloperRepository;
import com.smartjira.smartjira.repository.HrRepository;
import com.smartjira.smartjira.repository.SalaryRepository;
import com.smartjira.smartjira.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class HrService {
    private final HrRepository hrRepository;
    private final UserRepository userRepository;
    private final DeveloperRepository developerRepository;
    private final SalaryRepository salaryRepository;

    @Transactional
    public Developer createEmployee(UserHrDto dto) {
        // ️ Create and save User
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRoles(Role.DEVELOPER);
        User savedUser = userRepository.save(user);

        //  Create and save Developer linked to User
        Developer developer = new Developer();
        developer.setUser(savedUser);
        developer.setManager(null);
        Developer savedDeveloper = developerRepository.save(developer);

        //  Create and save Salary linked to Developer
        Salary salary = new Salary();
        salary.setDeveloper(savedDeveloper);
        salary.setSalary(dto.getSalary());
        salaryRepository.save(salary);

        return savedDeveloper;
    }

    public List<DeveloperDto> getAllDevelopers() {
        return hrRepository.getAllDeveloperinHrDashboad();
    }

    public List<DeveloperDto>SearchDeveloperInHrDashboard(String username) {
        return hrRepository.searchDeveloperInHrDashboard(username);
    }

    @Transactional
    public void affectDeveloperToManager(long managerId, long userId) {
        hrRepository.affectDeveloperManagerId(managerId, userId);
    }

    public List<ManagerDto> getAllManagers() {
        return hrRepository.getAllManagers();
    }

    public List<UserWithoutManagerDto> getAllDevelopersWithoutManagers() {
        return hrRepository.getAllDeveloperWithoutManagers();
    }


    // ----------------- Leave Requests -----------------
    public List<LeaveUserDto> getPendingLeaves() {
        return hrRepository.getPendingLeave();
    }

    public List<LeaveUserDto> getApprovedLeaves() {
        return hrRepository.getApprovedLeave();
    }

    public List<LeaveUserDto> getRejectedLeaves() {
        return hrRepository.getRejectedLeave();
    }

    // ----------------- Approve / Reject -----------------
    @Transactional
    public void approveAllPendingLeaves(int idDev) {
        hrRepository.updatePendingToApprove(idDev);
    }

    @Transactional
    public void rejectAllPendingLeaves(int idDev) {
        hrRepository.updatePendingToRejected(idDev);
    }

    public int countDeveloper(){
        return hrRepository.countDeveloper();
    }

    public  int countApprovedLeave(){
        return hrRepository.countApprovedLeave();
    }

    public int countPendingLeave(){
        return hrRepository.countPendingLeave();
    }


}
