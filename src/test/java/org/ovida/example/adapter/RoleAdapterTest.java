package org.ovida.example.adapter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ovida.example.domain.RoleEntity;
import org.ovida.example.domain.PermissionEntity;
import org.ovida.example.dto.RoleDto;

public class RoleAdapterTest {

    @Test
    void testToDto() {
        // Arrange
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleId(1);
        roleEntity.setRoleName("ADMIN");

        PermissionEntity permission1 = new PermissionEntity();
        permission1.setPermissionName("READ_PRIVILEGES");

        PermissionEntity permission2 = new PermissionEntity();
        permission2.setPermissionName("WRITE_PRIVILEGES");

        roleEntity.setPermissions(Arrays.asList(permission1, permission2));

        // Act
        RoleDto roleDto = RoleAdapter.toDto(roleEntity);

        // Assert
        assertNotNull(roleDto);
        assertEquals(1, roleDto.getRoleId());
        assertEquals("ADMIN", roleDto.getRoleName());
        assertNotNull(roleDto.getPermissions());
        assertEquals(2, roleDto.getPermissions().size());
        assertEquals("READ_PRIVILEGES", roleDto.getPermissions().get(0).getPermissionName());
        assertEquals("WRITE_PRIVILEGES", roleDto.getPermissions().get(1).getPermissionName());
    }

    @Test
    void testToDto_NullEntity() {
        // Arrange
        RoleEntity roleEntity = null;

        // Act
        RoleDto roleDto = RoleAdapter.toDto(roleEntity);

        // Assert
        assertNull(roleDto);
    }

    @Test
    void testToDTOs() {
        // Arrange
        RoleEntity role1 = new RoleEntity();
        role1.setRoleId(1);
        role1.setRoleName("ADMIN");

        RoleEntity role2 = new RoleEntity();
        role2.setRoleId(2);
        role2.setRoleName("USER");

        List<RoleEntity> roleEntities = Arrays.asList(role1, role2);

        // Act
        List<RoleDto> roleDtos = RoleAdapter.toDTOs(roleEntities);

        // Assert
        assertNotNull(roleDtos);
        assertEquals(2, roleDtos.size());
        assertEquals(1, roleDtos.get(0).getRoleId());
        assertEquals("ADMIN", roleDtos.get(0).getRoleName());
        assertEquals(2, roleDtos.get(1).getRoleId());
        assertEquals("USER", roleDtos.get(1).getRoleName());
    }

    @Test
    void testToDTOs_EmptyList() {
        // Arrange
        List<RoleEntity> roleEntities = Collections.emptyList();

        // Act
        List<RoleDto> roleDtos = RoleAdapter.toDTOs(roleEntities);

        // Assert
        assertNotNull(roleDtos);
        assertTrue(roleDtos.isEmpty());
    }

    @Test
    void testToDTOs_NullList() {
        // Arrange
        List<RoleEntity> roleEntities = null;

        // Act
        List<RoleDto> roleDtos = RoleAdapter.toDTOs(roleEntities);

        // Assert
        assertNotNull(roleDtos);
        assertTrue(roleDtos.isEmpty());
    }
}
