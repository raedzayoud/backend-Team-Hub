package com.smartjira.smartjira.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaveReason {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Reason cannot be null")
    private String reason;

    @ManyToOne
    @JoinColumn(name = "developer_id",unique = true)
    private Developer developer;



}
