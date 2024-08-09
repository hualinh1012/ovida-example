package org.ovida.example.adapter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ovida.example.domain.RoleEntity;
import org.ovida.example.domain.UserEntity;
import org.ovida.example.dto.UserDto;

public class UserAdapterTest {

    @Test
    void testToDto() {
        // Arrange
        UserEntity entity = new UserEntity();
        entity.setUserId(1);
        entity.setUserName("john_doe");

        RoleEntity role1 = new RoleEntity();
        role1.setRoleName("ADMIN");

        RoleEntity role2 = new RoleEntity();
        role2.setRoleName("USER");

        entity.setRoles(Arrays.asList(role1, role2));

        // Act
        UserDto dto = UserAdapter.toDto(entity);

        // Assert
        assertNotNull(dto);
        assertEquals(1, dto.getUserId());
        assertEquals("john_doe", dto.getUserName());
        assertNotNull(dto.getRoles());
        assertEquals(2, dto.getRoles().size());
        assertEquals("ADMIN", dto.getRoles().get(0).getRoleName());
        assertEquals("USER", dto.getRoles().get(1).getRoleName());
    }

    @Test
    void testToDto_NullEntity() {
        // Arrange
        UserEntity entity = null;

        // Act
        UserDto dto = UserAdapter.toDto(entity);

        // Assert
        assertNull(dto);
    }

    @Test
    void testToDTOs() {
        // Arrange
        UserEntity user1 = new UserEntity();
        user1.setUserId(1);
        user1.setUserName("john_doe");

        UserEntity user2 = new UserEntity();
        user2.setUserId(2);
        user2.setUserName("jane_doe");

        List<UserEntity> entities = Arrays.asList(user1, user2);

        // Act
        List<UserDto> dtos = UserAdapter.toDTOs(entities);

        // Assert
        assertNotNull(dtos);
        assertEquals(2, dtos.size());
        assertEquals(1, dtos.get(0).getUserId());
        assertEquals("john_doe", dtos.get(0).getUserName());
        assertEquals(2, dtos.get(1).getUserId());
        assertEquals("jane_doe", dtos.get(1).getUserName());
    }

    @Test
    void testToDTOs_EmptyList() {
        // Arrange
        List<UserEntity> entities = Collections.emptyList();

        // Act
        List<UserDto> dtos = UserAdapter.toDTOs(entities);

        // Assert
        assertNotNull(dtos);
        assertTrue(dtos.isEmpty());
    }

    @Test
    void testToDTOs_NullList() {
        // Arrange
        List<UserEntity> entities = null;

        // Act
        List<UserDto> dtos = UserAdapter.toDTOs(entities);

        // Assert
        assertNotNull(dtos);
        assertTrue(dtos.isEmpty());
    }
}
