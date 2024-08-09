package org.ovida.example.service;

import org.ovida.example.dto.RoleDto;

import java.util.List;

public interface IRoleService {

    List<RoleDto> getAllRoles();

    void addRolePermission(int roleId, int permissionId);

    void deleteRolePermission(int roleId, int permissionId);
}
