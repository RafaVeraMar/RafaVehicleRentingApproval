package com.babel.vehiclerentingapproval.Security.Service;

import com.babel.vehiclerentingapproval.Security.models.Usuario;

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
    public UserDetails loadUserByUsername(String email, String password) {
        Usuario usuario = new Usuario();
       usuario.setPersonaId(validateEmail(email));
       usuario.setPassword(validatePassword(password));
        return new UserDetailsImpl(usuario);
    }

    public int validateEmail (String email)  {
        int Id = personaMapper.getPersonaId(email);
        if (Id>0) { return Id;
        }else {throw new UsernameNotFoundException("No se ha encontrado una persona con ese email");}
    }

    public String validatePassword (String password){
        String passwordBD = personaMapper.getPassword(password);
        if (passwordBD != null){
    }else{}


}
