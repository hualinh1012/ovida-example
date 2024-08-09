package org.ovida.example.security;

import lombok.extern.slf4j.Slf4j;
import org.ovida.example.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Custom implementation for AuthenticationProvider
 * This class will help Spring Security access database and verify the user
 */

@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider
{
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException
    {
        final String name = authentication.getName();
        final String password = authentication.getCredentials().toString();

        UserDetails principal = userDetailsService.loadUserByUsername(name);

        if (isPasswordMatches(principal, password))
        {
            return new UsernamePasswordAuthenticationToken(principal, password, principal.getAuthorities());
        }

        log.info("Authentication failed for user: {}", name);

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private static boolean isPasswordMatches(UserDetails principal, String password)
    {
        return principal != null && principal.getPassword().equals(password);
    }
}
