package com.babel.vehiclerentingapproval.Security.Service;
import com.babel.vehiclerentingapproval.Security.usuario.Usuario;
import com.babel.vehiclerentingapproval.models.Persona;
import com.babel.vehiclerentingapproval.persistance.database.mappers.PersonaMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
/**
 * Implementación de la interfaz UserDetailsService que carga los detalles del usuario por nombre de usuario.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * Mapper utilizado para acceder a los datos de la entidad Persona.
     */
    @Autowired
    private PersonaMapper personaMapper;

    /**
     * Crea una nueva instancia de UserDetailsServiceImpl con el mapper de Persona especificado.
     *
     * @param personaMapper Mapper de Persona utilizado para acceder a los datos de la entidad Persona.
     */
    public UserDetailsServiceImpl(PersonaMapper personaMapper) {
        this.personaMapper = personaMapper;
    }

    /**
     * Carga los detalles del usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario del usuario.
     * @return Detalles del usuario como UserDetails.
     * @throws UsernameNotFoundException Si no se encuentra al usuario.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Persona persona = personaMapper.findEmailByEmail(username);
        if (persona != null) {
            List<GrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            return new UserDetailsImpl(persona.getEmail(), persona.getNombre(), persona.getPassword());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
