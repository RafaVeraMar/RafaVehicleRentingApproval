package com.babel.vehiclerentingapproval.Security.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Implementación de la interfaz UserDetails que representa los detalles del usuario.
 */
public class UserDetailsImpl implements UserDetails {

    /**
     * Correo electrónico del usuario.
     */
    private final String email;

    /**
     * Contraseña del usuario.
     */
    private final String password;

    /**
     * Nombre del usuario.
     */
    private final String nombre;

    /**
     * Crea una nueva instancia de UserDetailsImpl con los detalles del usuario.
     *
     * @param email    Correo electrónico del usuario.
     * @param nombre   Nombre del usuario.
     * @param password Contraseña del usuario.
     */
    public UserDetailsImpl(String email, String nombre, String password) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
    }

    /**
     * Devuelve la colección de autoridades concedidas al usuario.
     *
     * @return Colección de autoridades del usuario.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * Devuelve la contraseña del usuario.
     *
     * @return Contraseña del usuario.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Devuelve el nombre de usuario del usuario.
     *
     * @return Nombre de usuario del usuario.
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Indica si la cuenta del usuario ha expirado.
     *
     * @return true si la cuenta no ha expirado, false en caso contrario.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica si la cuenta del usuario está bloqueada.
     *
     * @return true si la cuenta no está bloqueada, false en caso contrario.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica si las credenciales del usuario han expirado.
     *
     * @return true si las credenciales no han expirado, false en caso contrario.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indica si el usuario está habilitado.
     *
     * @return true si el usuario está habilitado, false en caso contrario.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Devuelve el nombre del usuario.
     *
     * @return Nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }
}

