package com.smartjira.smartjira.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Salary must be greater than zero")
    @Column(nullable = false)
    private double salary;

    @NotNull(message = "Developer must not be null")
    @OneToOne
    @JoinColumn(name = "developer_id", unique = true, nullable = false)
    private Developer developer;
}
