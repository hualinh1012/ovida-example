package org.ovida.example.adapter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ovida.example.domain.PermissionEntity;
import org.ovida.example.dto.PermissionDto;

public class PermissionAdapterTest {

    @Test
    void testToDto() {
        // Arrange
        PermissionEntity permissionEntity = new PermissionEntity();
        permissionEntity.setPermissionId(1);
        permissionEntity.setPermissionName("READ_PRIVILEGES");
        permissionEntity.setDescription("Allows read access");

        // Act
        PermissionDto permissionDto = PermissionAdapter.toDto(permissionEntity);

        // Assert
        assertNotNull(permissionDto);
        assertEquals(1, permissionDto.getPermissionId());
        assertEquals("READ_PRIVILEGES", permissionDto.getPermissionName());
        assertEquals("Allows read access", permissionDto.getDescription());
    }

    @Test
    void testToDto_NullEntity() {
        // Arrange
        PermissionEntity permissionEntity = null;

        // Act
        PermissionDto permissionDto = PermissionAdapter.toDto(permissionEntity);

        // Assert
        assertNull(permissionDto);
    }

    @Test
    void testToDTOs() {
        // Arrange
        PermissionEntity permission1 = new PermissionEntity();
        permission1.setPermissionId(1);
        permission1.setPermissionName("READ_PRIVILEGES");
        permission1.setDescription("Allows read access");

        PermissionEntity permission2 = new PermissionEntity();
        permission2.setPermissionId(2);
        permission2.setPermissionName("WRITE_PRIVILEGES");
        permission2.setDescription("Allows write access");

        List<PermissionEntity> permissionEntities = Arrays.asList(permission1, permission2);

        // Act
        List<PermissionDto> permissionDtos = PermissionAdapter.toDTOs(permissionEntities);

        // Assert
        assertNotNull(permissionDtos);
        assertEquals(2, permissionDtos.size());

        PermissionDto[] permissionDtoArray = permissionDtos.toArray(new PermissionDto[0]);
        assertEquals(1, permissionDtoArray[0].getPermissionId());
        assertEquals("READ_PRIVILEGES", permissionDtoArray[0].getPermissionName());
        assertEquals("Allows read access", permissionDtoArray[0].getDescription());

        assertEquals(2, permissionDtoArray[1].getPermissionId());
        assertEquals("WRITE_PRIVILEGES", permissionDtoArray[1].getPermissionName());
        assertEquals("Allows write access", permissionDtoArray[1].getDescription());
    }

    @Test
    void testToDTOs_EmptyList() {
        // Arrange
        List<PermissionEntity> permissionEntities = Collections.emptyList();

        // Act
        List<PermissionDto> permissionDtos = PermissionAdapter.toDTOs(permissionEntities);

        // Assert
        assertNotNull(permissionDtos);
        assertTrue(permissionDtos.isEmpty());
    }

    @Test
    void testToDTOs_NullList() {
        // Arrange
        List<PermissionEntity> permissionEntities = null;

        // Act
        List<PermissionDto> permissionDtos = PermissionAdapter.toDTOs(permissionEntities);

        // Assert
        assertNotNull(permissionDtos);
        assertTrue(permissionDtos.isEmpty());
    }
}
