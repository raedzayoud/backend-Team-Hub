package com.smartjira.smartjira.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private Manager manager;
    @JsonIgnore
    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL)
    private List<Tasks> tasks;

}
