package com.babel.vehiclerentingapproval.Security.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

public class Usuario {
    @NotNull
    @NotEmpty
    @NotBlank
    @Positive
    @Getter
    @Setter
    private int personaId;

    @Size(max = 50)
    @NotNull
    @NotEmpty
    @NotBlank
    @Getter    @Setter
    private String nombre;

    @Getter    @Setter
    private String email;

    @Getter @Setter
    private String password;
}
