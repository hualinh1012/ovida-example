package org.ovida.example.adapter;

import org.ovida.example.domain.RoleEntity;
import org.ovida.example.dto.RoleDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter class to convert between RoleEntity and RoleEntity
 */
public class RoleAdapter {

    public static RoleDto toDto(RoleEntity entity) {
        if (entity == null) {
            return null;
        }
        RoleDto dto = new RoleDto();
        dto.setRoleId(entity.getRoleId());
        dto.setRoleName(entity.getRoleName());
        dto.setPermissions(PermissionAdapter.toDTOs(entity.getPermissions()));
        return dto;
    }

    public static List<RoleDto> toDTOs(List<RoleEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return new ArrayList<>();
        }
        List<RoleDto> list = new ArrayList<>();
        entities.forEach(entity -> list.add(toDto(entity)));
        return list;
    }
}
