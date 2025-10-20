package com.smartjira.smartjira.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.scheduling.config.Task;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "developer cannot be a null ")
    @OneToOne
    @JoinColumn(name = "user_id",unique = true)
    private User user;

    //Means: “The other entity (LeaveReason) owns this relationship via its field named developer.”
    @OneToMany(mappedBy = "developer" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LeaveReason> leaveReasons;
    // optional
    @OneToOne(mappedBy = "developer",cascade = CascadeType.ALL)
    private Salary salary;

    @OneToMany(mappedBy = "developer",cascade = CascadeType.ALL)
    private List<Tasks>tasks;

    @ManyToOne
    @JoinColumn(name = "manager_id",unique = true,nullable = true)
    private Manager manager;

}
