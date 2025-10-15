package com.smartjira.smartjira.repository;

import com.smartjira.smartjira.model.LeaveReason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveReasonRepository extends JpaRepository<LeaveReason,Integer> {
}
