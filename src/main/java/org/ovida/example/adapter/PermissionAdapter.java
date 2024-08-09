package org.ovida.example.adapter;

import org.ovida.example.domain.PermissionEntity;
import org.ovida.example.dto.PermissionDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Adapter class to convert between PermissionEntity and PermissionDto
 */
public class PermissionAdapter {

    public static PermissionDto toDto(PermissionEntity entity) {
        if (entity == null) {
            return null;
        }
        PermissionDto dto = new PermissionDto();
        dto.setPermissionId(entity.getPermissionId());
        dto.setPermissionName(entity.getPermissionName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static List<PermissionDto> toDTOs(List<PermissionEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return new ArrayList<>();
        }
        List<PermissionDto> list = new ArrayList<>();
        entities.forEach(entity -> list.add(toDto(entity)));
        return list;
    }
}
