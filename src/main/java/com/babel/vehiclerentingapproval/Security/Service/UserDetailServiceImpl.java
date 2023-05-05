package com.babel.vehiclerentingapproval.Security.Service;

import com.babel.vehiclerentingapproval.exceptions.PersonByEmailNotFoundException;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PersonaMapper personaMapper;


    @Override
    public UserDetails loadUserByUsername(String email) throws PersonByEmailNotFoundException {
       Persona persona = personaMapper.getPersonaId(email);
        return null;
    }
}
