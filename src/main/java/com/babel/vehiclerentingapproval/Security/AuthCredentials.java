package com.babel.vehiclerentingapproval.Security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;/**
 * Clase que representa las credenciales de autenticación del usuario.
 */
@Data
public class AuthCredentials {

    /**
     * Correo electrónico del usuario.
     */
    private String email;

    /**
     * Contraseña del usuario.
     */
    private String password;
}
