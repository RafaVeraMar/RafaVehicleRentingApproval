package com.babel.vehiclerentingapproval.Security.Service;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private PersonaMapper personaMapper;

    public UserDetailsServiceImpl(PersonaMapper personaMapper) {
        this.personaMapper = personaMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User persona = personaMapper.findEmailByEmail(username);
        if (persona != null) {
            List<GrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            return new User(persona.getUsername(), persona.getPassword(), roles);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}

