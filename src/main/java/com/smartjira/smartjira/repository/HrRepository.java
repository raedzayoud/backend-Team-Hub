package com.smartjira.smartjira.repository;

import com.smartjira.smartjira.model.Developer;
import com.smartjira.smartjira.model.Hr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HrRepository extends JpaRepository<Hr,Long> {}
