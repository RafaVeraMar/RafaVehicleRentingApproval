package com.babel.vehiclerentingapproval.Security;

import com.babel.vehiclerentingapproval.Security.AuthCredentials;
import com.babel.vehiclerentingapproval.Security.Service.UserDetailsImpl;
import com.babel.vehiclerentingapproval.Security.TokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;
/**
 * Filtro de autenticación JWT que extiende UsernamePasswordAuthenticationFilter.
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * Intenta autenticar al usuario utilizando las credenciales proporcionadas en la solicitud HTTP.
     *
     * @param request  Objeto HttpServletRequest que representa la solicitud HTTP.
     * @param response Objeto HttpServletResponse que representa la respuesta HTTP.
     * @return Objeto Authentication que representa la autenticación exitosa.
     * @throws AuthenticationException Si se produce un error durante la autenticación.
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            AuthCredentials authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
            if (StringUtils.isEmpty(authCredentials.getEmail()) || StringUtils.isEmpty(authCredentials.getPassword())) {
                throw new BadCredentialsException("Email y contraseña son campos requeridos");
            }
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                    authCredentials.getEmail(),
                    authCredentials.getPassword(),
                    Collections.emptyList()
            ));
        } catch (IOException e) {
            throw new AuthenticationServiceException("Error al intentar leer el cuerpo de la solicitud HTTP", e);
        }

    }

    /**
     * Se llama cuando la autenticación es exitosa. Genera un token JWT y lo agrega como encabezado de autorización en la respuesta HTTP.
     *
     * @param request     Objeto HttpServletRequest que representa la solicitud HTTP.
     * @param response    Objeto HttpServletResponse que representa la respuesta HTTP.
     * @param chain       Objeto FilterChain utilizado para continuar con la cadena de filtros.
     * @param authResult  Objeto Authentication que representa la autenticación exitosa.
     * @throws IOException      Si se produce un error de E/S durante la escritura de la respuesta.
     * @throws ServletException Si se produce un error durante el procesamiento del servlet.
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String token = TokenUtils.createToken(userDetails.getNombre(), userDetails.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
