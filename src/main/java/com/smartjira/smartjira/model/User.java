package com.smartjira.smartjira.model;

import com.smartjira.smartjira.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

  //  @NotBlank(message = "Le nom ne peut pas être vide")
   // @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
    private String name;

    //@NotBlank(message = "L'email ne peut pas être vide")
   // @Email(message = "Email invalide")
    //@Column(nullable = false, unique = true)
    private String email;

   // @NotBlank(message = "Le mot de passe ne peut pas être vide")
  //  @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
    private String password;

    //@Enumerated(EnumType.STRING)
    private Role roles;

}
