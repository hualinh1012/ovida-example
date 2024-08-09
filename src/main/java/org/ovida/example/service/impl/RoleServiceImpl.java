package org.ovida.example.service.impl;

import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import org.ovida.example.adapter.RoleAdapter;
import org.ovida.example.domain.PermissionEntity;
import org.ovida.example.domain.RoleEntity;
import org.ovida.example.domain.RolePermissionEntity;
import org.ovida.example.dto.RoleDto;
import org.ovida.example.repository.IPermissionRepository;
import org.ovida.example.repository.IRolePermissionRepository;
import org.ovida.example.repository.IRoleRepository;
import org.ovida.example.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.ovida.example.constant.RoleConstant.ADMIN;

@Service
@Transactional
@RolesAllowed(ADMIN)
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IPermissionRepository permissionRepository;

    @Autowired
    private IRolePermissionRepository rolePermissionRepository;

    @Override
    public List<RoleDto> getAllRoles() {
        return RoleAdapter.toDTOs(roleRepository.findAll());
    }

    @Override
    public void addRolePermission(int roleId, int permissionId) {
        RoleEntity role = roleRepository.findByRoleId(roleId);
        PermissionEntity permission = permissionRepository.findByPermissionId(permissionId);
        if (role == null || permission == null) {
            throw new IllegalArgumentException("Invalid role and permission!");
        }

        boolean isPermissionExists = role.getPermissions().stream()
                .anyMatch(permissionEntity -> permissionEntity.getPermissionId() == permissionId);
        if (!isPermissionExists) {
            RolePermissionEntity rolePermission = new RolePermissionEntity();
            rolePermission.setPermissionId(permissionId);
            rolePermission.setRoleId(roleId);
            rolePermissionRepository.save(rolePermission);
        }
    }

    @Override
    public void deleteRolePermission(int roleId, int permissionId) {
        rolePermissionRepository.deleteByRoleIdAndPermissionId(roleId, permissionId);
    }

}
