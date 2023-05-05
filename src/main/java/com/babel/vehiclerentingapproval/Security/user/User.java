package com.babel.vehiclerentingapproval.Security.user;

import jakarta.persistence.Entity;
import lombok.*;

import javax.validation.constraints.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    private int personaId;
    private String nombre;
    private String email;
    private String password;
}
