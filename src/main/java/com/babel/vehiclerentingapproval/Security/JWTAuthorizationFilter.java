package com.babel.vehiclerentingapproval.Security;

import com.babel.vehiclerentingapproval.Security.TokenUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Filtro de autorización JWT que se ejecuta una vez por cada solicitud.
 */
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    /**
     * Filtra internamente la solicitud y establece la autenticación en el contexto de seguridad si se proporciona un token JWT válido en el encabezado de autorización.
     *
     * @param request     Objeto HttpServletRequest que representa la solicitud HTTP.
     * @param response    Objeto HttpServletResponse que representa la respuesta HTTP.
     * @param filterChain Objeto FilterChain utilizado para continuar con la cadena de filtros.
     * @throws ServletException Si se produce un error durante el procesamiento del servlet.
     * @throws IOException      Si se produce un error de E/S durante el filtrado de la solicitud o respuesta.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.replace("Bearer ", "");
            UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(usernamePAT);
        }
        filterChain.doFilter(request, response);
    }
}
