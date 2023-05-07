package com.babel.vehiclerentingapproval.Security;

import com.babel.vehiclerentingapproval.Security.AuthCredentials;
import com.babel.vehiclerentingapproval.Security.Service.UserDetailsImpl;
import com.babel.vehiclerentingapproval.Security.TokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

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
