package com.babel.vehiclerentingapproval.Security;

import com.babel.vehiclerentingapproval.Security.Service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/**
 * Configuración de seguridad web para la aplicación.
 */
@Configuration
@AllArgsConstructor
public class WebSecurityConfig  {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    /**
     * Constructor que recibe una implementación de UserDetailsService.
     *
     * @param userDetailsService Implementación de UserDetailsService.
     */
    public void SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Configura el AuthenticationManager para utilizar la implementación de UserDetailsService.
     *
     * @param auth Objeto AuthenticationManagerBuilder.
     * @throws Exception Si se produce un error durante la configuración del AuthenticationManager.
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * Configura el filtro de seguridad y la cadena de filtros de seguridad HTTP.
     *
     * @param http        Objeto HttpSecurity utilizado para la configuración de seguridad.
     * @param authManager Objeto AuthenticationManager utilizado para la autenticación.
     * @return Objeto SecurityFilterChain que representa la cadena de filtros de seguridad configurada.
     * @throws Exception Si se produce un error durante la configuración de seguridad.
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/actuator/prometheus", "/actuator/metrics").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Configura el AuthenticationManager utilizando la implementación de UserDetailsService y el PasswordEncoder.
     *
     * @param http           Objeto HttpSecurity utilizado para la configuración de seguridad.
     * @param passwordEncoder Objeto PasswordEncoder utilizado para el encriptado de contraseñas.
     * @return Objeto AuthenticationManager configurado.
     * @throws Exception Si se produce un error durante la configuración del AuthenticationManager.
     */
    @Bean
    AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    /**
     * Crea un objeto PasswordEncoder utilizando el algoritmo BCrypt.
     *
     * @return Objeto PasswordEncoder configurado con el algoritmo BCrypt.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
