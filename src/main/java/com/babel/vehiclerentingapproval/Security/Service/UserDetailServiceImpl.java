package com.babel.vehiclerentingapproval.Security.Service;

import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private PersonaMapper personaMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Persona persona = personaMapper.getPersonaByEmail(email);
        if (persona == null) {
            throw new UsernameNotFoundException("No se ha encontrado una persona con ese email");
        }
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(String.valueOf(persona.getPersonaId()), persona.getPassword(), authorities);
    }

    public boolean autenticar(String email, String password) {
        Persona persona = personaMapper.getPersonaByEmail(email);
        if (persona == null) {
            return false;
        }
        // Validar que el campo password no está vacío
        if (StringUtils.isEmpty(persona.getPassword())) {
            return false;
        }
        return passwordEncoder.matches(password, persona.getPassword());
    }

}

