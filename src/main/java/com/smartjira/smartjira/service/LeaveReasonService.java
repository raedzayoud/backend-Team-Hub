package com.smartjira.smartjira.service;

import com.smartjira.smartjira.model.LeaveReason;
import com.smartjira.smartjira.repository.LeaveReasonRepository;
import org.springframework.stereotype.Service;

@Service
public class LeaveReasonService {
    private LeaveReasonRepository leaveReasonRepository;

    public LeaveReason SaveLeaveReason(LeaveReason leaveReason){
        return  leaveReasonRepository.save(leaveReason);
    }
}
