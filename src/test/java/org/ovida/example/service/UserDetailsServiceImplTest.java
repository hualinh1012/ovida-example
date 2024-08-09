package org.ovida.example.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.ovida.example.domain.RoleEntity;
import org.ovida.example.domain.UserEntity;
import org.ovida.example.repository.IUserRepository;
import org.ovida.example.service.impl.UserDetailsServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private IUserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername_UserExistsWithRoles() {
        // Arrange
        String username = "john_doe";
        String password = "secure_password";

        RoleEntity role1 = new RoleEntity();
        role1.setRoleName("ADMIN");

        RoleEntity role2 = new RoleEntity();
        role2.setRoleName("USER");

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(username);
        userEntity.setPassword(password);
        userEntity.setRoles(Arrays.asList(role1, role2));

        when(userRepository.findUserByUserName(username)).thenReturn(userEntity);

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN")));
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void testLoadUserByUsername_UserDoesNotExist() {
        // Arrange
        String username = "non_existent_user";

        when(userRepository.findUserByUserName(username)).thenReturn(null);

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Assert
        assertNull(userDetails);
    }

    @Test
    void testLoadUserByUsername_UserExistsWithoutRoles() {
        // Arrange
        String username = "jane_doe";
        String password = "another_secure_password";

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(username);
        userEntity.setPassword(password);
        userEntity.setRoles(Collections.emptyList());

        when(userRepository.findUserByUserName(username)).thenReturn(userEntity);

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty());
    }
}
