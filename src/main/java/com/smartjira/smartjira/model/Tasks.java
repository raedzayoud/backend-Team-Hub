    package com.smartjira.smartjira.model;

    import com.fasterxml.jackson.annotation.JsonBackReference;
    import com.fasterxml.jackson.annotation.JsonIgnore;
    import com.smartjira.smartjira.enums.Role;
    import com.smartjira.smartjira.enums.Status;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.*;
    import lombok.*;

    @Entity
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class Tasks {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @NotBlank(message = "Task name is required")
        @Size(min = 3, max = 100, message = "Task name must be between 3 and 100 characters")
        private String name;

        @ManyToOne
        @JoinColumn(name = "developer_id", nullable = false)
        @NotNull(message = "Developer must be assigned to the task")
        @JsonIgnore
        private Developer developer;

        @Enumerated(EnumType.STRING)
        private Status status;

        @ManyToOne
        @JoinColumn(name = "projet_id", nullable = false)
        @JsonIgnore
        private Project project;


    }
