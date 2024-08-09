package org.ovida.example.adapter;

import org.ovida.example.domain.UserEntity;
import org.ovida.example.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter class to convert between UserDto and UserEntity
 */
public class UserAdapter {

    public static UserDto toDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setUserId(entity.getUserId());
        dto.setUserName(entity.getUserName());
        dto.setDescription(entity.getDescription());
        dto.setRoles(RoleAdapter.toDTOs(entity.getRoles()));
        return dto;
    }

    public static List<UserDto> toDTOs(List<UserEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return new ArrayList<>();
        }
        List<UserDto> list = new ArrayList<>();
        entities.forEach(entity -> list.add(toDto(entity)));
        return list;
    }
}
