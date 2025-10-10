package com.smartjira.smartjira.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "manager cannot be a null ")
    @OneToOne
    @JoinColumn(name = "user_id",unique = true)
    private User user;

}
