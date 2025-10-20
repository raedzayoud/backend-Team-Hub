    package com.smartjira.smartjira.controller;

    import com.smartjira.smartjira.dto.LeaveDto;
    import com.smartjira.smartjira.enums.StatusLeave;
    import com.smartjira.smartjira.model.Developer;
    import com.smartjira.smartjira.model.LeaveReason;
    import com.smartjira.smartjira.service.DeveloperService;
    import com.smartjira.smartjira.service.LeaveReasonService;
    import lombok.AllArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.Map;

    @AllArgsConstructor
    @RestController
    @RequestMapping("/api/v1/leave/")
    public class LeaveReasonController {

        private final LeaveReasonService leaveReasonService;
        private final DeveloperService  developerService;

        @PostMapping
        public ResponseEntity<Map<String, String>> saveLeaveReason(@RequestBody LeaveDto leaveDto) {

            LeaveReason leaveReason = new LeaveReason();
            leaveReason.setReason(leaveDto.getReason());
            leaveReason.setNbDays(leaveDto.getNbDays());

            // Set Developer
            Developer developer = developerService.findById(leaveDto.getDeveloper_id());
            leaveReason.setDeveloper(developer);

            // Set initial status to Pending
            leaveReason.setStatus(StatusLeave.Pending);

            // Save leave reason
            leaveReasonService.SaveLeaveReason(leaveReason);

            return ResponseEntity.ok(Map.of("message", "Leave saved successfully with status Pending"));
        }

    }
