package com.babel.vehiclerentingapproval.Security.user;
import com.babel.vehiclerentingapproval.models.Persona;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class User extends Persona implements UserDetails {
    private List<GrantedAuthority> authorities;

    public User(Persona persona, List<GrantedAuthority> authorities) {
        super(persona.getPersonaId(), persona.getNombre(), persona.getEmail(), persona.getPassword());
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}