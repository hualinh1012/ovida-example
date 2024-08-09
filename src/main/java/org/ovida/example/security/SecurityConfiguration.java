package org.ovida.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration for Spring Security
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    @Autowired
    AuthenticationProvider authProvider;

    /**
     * We configure our CustomAuthenticationProvider here
     * @param httpSecurity Inject HttpSecurity from Spring Boot and custom it
     * @return AuthenticationManager
     */
    @Bean
    public AuthenticationManager authManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                        .authenticationProvider(authProvider)
                        .eraseCredentials(false);
        return authenticationManagerBuilder.build();
    }

    /**
     * We configure our filter here
     * @param httpSecurity Inject HttpSecurity from Spring Boot and custom it
     * @param authenticationManager from `authManager` Bean
     * @return SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(
                        (requests) -> requests
                                .anyRequest()
                                .authenticated())
                .httpBasic(Customizer.withDefaults())
                .csrf(CsrfConfigurer::disable)
                .authenticationManager(authenticationManager)
                .build();
    }
}
