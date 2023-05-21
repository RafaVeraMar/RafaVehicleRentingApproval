package com.babel.vehiclerentingapproval.Security.usuario;
import com.babel.vehiclerentingapproval.models.Persona;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Clase que representa un usuario, que extiende la clase Persona e implementa UserDetails.
 */
public class Usuario extends Persona implements UserDetails {

    private List<GrantedAuthority> authorities;

    /**
     * Constructor de la clase Usuario.
     *
     * @param persona     Objeto de tipo Persona que contiene la información del usuario.
     * @param authorities Lista de autoridades/granteds del usuario.
     */
    public Usuario(Persona persona, List<GrantedAuthority> authorities) {
        super(persona.getNombre(), persona.getEmail(), persona.getPassword());
        this.authorities = authorities;
    }

    /**
     * Devuelve la lista de autoridades/granteds del usuario.
     *
     * @return Lista de autoridades/granteds del usuario.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Devuelve el nombre de usuario del usuario.
     *
     * @return El nombre de usuario del usuario.
     */
    @Override
    public String getUsername() {
        return getEmail();
    }

    /**
     * Indica si la cuenta del usuario ha expirado.
     *
     * @return Siempre devuelve true, lo que indica que la cuenta no ha expirado.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica si la cuenta del usuario está bloqueada.
     *
     * @return Siempre devuelve true, lo que indica que la cuenta no está bloqueada.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica si las credenciales del usuario han expirado.
     *
     * @return Siempre devuelve true, lo que indica que las credenciales no han expirado.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indica si el usuario está habilitado.
     *
     * @return Siempre devuelve true, lo que indica que el usuario está habilitado.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
