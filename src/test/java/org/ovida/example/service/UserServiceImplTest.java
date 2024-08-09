package org.ovida.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.ovida.example.domain.UserEntity;
import org.ovida.example.dto.UserDto;
import org.ovida.example.repository.IUserRepository;
import org.ovida.example.service.impl.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testGetCurrentUser() {
        // Arrange
        String currentUsername = "john_doe";
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(currentUsername);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(currentUsername);
        when(userRepository.findUserByUserName(currentUsername)).thenReturn(userEntity);

        // Act
        UserDto userDto = userService.getCurrentUser();

        // Assert
        assertNotNull(userDto);
        assertEquals(currentUsername, userDto.getUserName());
        verify(userRepository, times(1)).findUserByUserName(currentUsername);
    }

    @Test
    void testGetCurrentUser_UserNotFound() {
        // Arrange
        String currentUsername = "non_existent_user";

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(currentUsername);
        when(userRepository.findUserByUserName(currentUsername)).thenReturn(null);

        // Act
        UserDto userDto = userService.getCurrentUser();

        // Assert
        assertNull(userDto);
        verify(userRepository, times(1)).findUserByUserName(currentUsername);
    }

    @Test
    void testGetAllUser() {
        // Arrange
        UserEntity user1 = new UserEntity();
        user1.setUserName("john_doe");

        UserEntity user2 = new UserEntity();
        user2.setUserName("jane_doe");

        List<UserEntity> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<UserDto> userDtos = userService.getAllUser();

        // Assert
        assertNotNull(userDtos);
        assertEquals(2, userDtos.size());
        assertEquals("john_doe", userDtos.get(0).getUserName());
        assertEquals("jane_doe", userDtos.get(1).getUserName());
    }

}