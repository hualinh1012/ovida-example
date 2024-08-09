package org.ovida.example.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.ovida.example.service.impl.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomAuthenticationProviderTest {

    @InjectMocks
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthenticate_Success() {
        // Arrange
        String username = "john_doe";
        String password = "correct_password";

        UserDetails userDetails = mock(UserDetails.class);
        when(authentication.getName()).thenReturn(username);
        when(authentication.getCredentials()).thenReturn(password);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn(username);
        when(userDetails.getPassword()).thenReturn(password);

        // Act
        Authentication result = customAuthenticationProvider.authenticate(authentication);

        // Assert
        assertNotNull(result);
        assertEquals(username, result.getName());
        assertEquals(password, result.getCredentials());
        assertEquals(userDetails.getAuthorities(), result.getAuthorities());
    }

    @Test
    void testAuthenticate_Failure_WrongPassword() {
        // Arrange
        String username = "john_doe";
        String password = "wrong_password";

        UserDetails userDetails = mock(UserDetails.class);
        when(authentication.getName()).thenReturn(username);
        when(authentication.getCredentials()).thenReturn(password);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(userDetails.getPassword()).thenReturn("correct_password");

        // Act
        Authentication result = customAuthenticationProvider.authenticate(authentication);

        // Assert
        assertNull(result);
        verify(userDetailsService, times(1)).loadUserByUsername(username);
    }

    @Test
    void testAuthenticate_Failure_UserNotFound() {
        // Arrange
        String username = "non_existent_user";
        String password = "password";

        when(authentication.getName()).thenReturn(username);
        when(authentication.getCredentials()).thenReturn(password);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(null);

        // Act
        Authentication result = customAuthenticationProvider.authenticate(authentication);

        // Assert
        assertNull(result);
        verify(userDetailsService, times(1)).loadUserByUsername(username);
    }

    @Test
    void testSupports() {
        // Act & Assert
        assertTrue(customAuthenticationProvider.supports(UsernamePasswordAuthenticationToken.class));
        assertFalse(customAuthenticationProvider.supports(String.class)); // Should not support non-Authentication classes
    }
}
