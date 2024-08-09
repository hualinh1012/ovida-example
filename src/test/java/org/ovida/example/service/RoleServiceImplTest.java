package org.ovida.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.ovida.example.domain.PermissionEntity;
import org.ovida.example.domain.RoleEntity;
import org.ovida.example.domain.RolePermissionEntity;
import org.ovida.example.dto.RoleDto;
import org.ovida.example.repository.IPermissionRepository;
import org.ovida.example.repository.IRolePermissionRepository;
import org.ovida.example.repository.IRoleRepository;
import org.ovida.example.service.impl.RoleServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RoleServiceImplTest {

    @Mock
    private IRoleRepository roleRepository;

    @Mock
    private IPermissionRepository permissionRepository;

    @Mock
    private IRolePermissionRepository rolePermissionRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRoles() {
        RoleEntity role1 = new RoleEntity();
        role1.setRoleId(1);
        role1.setRoleName("Admin");

        RoleEntity role2 = new RoleEntity();
        role2.setRoleId(2);
        role2.setRoleName("User");

        List<RoleEntity> roles = Arrays.asList(role1, role2);
        when(roleRepository.findAll()).thenReturn(roles);

        List<RoleDto> roleDtos = roleService.getAllRoles();

        assertNotNull(roleDtos);
        assertEquals(2, roleDtos.size());
        assertEquals("Admin", roleDtos.get(0).getRoleName());
        assertEquals("User", roleDtos.get(1).getRoleName());
    }

    @Test
    void testAddRolePermission() {
        int roleId = 1;
        int permissionId = 100;

        RoleEntity role = new RoleEntity();
        role.setRoleId(roleId);

        PermissionEntity permission = new PermissionEntity();
        permission.setPermissionId(permissionId);

        when(roleRepository.findByRoleId(roleId)).thenReturn(role);
        when(permissionRepository.findByPermissionId(permissionId)).thenReturn(permission);

        roleService.addRolePermission(roleId, permissionId);

        verify(rolePermissionRepository, times(1)).save(any(RolePermissionEntity.class));
    }

    @Test
    void testAddRolePermissionInvalidRoleOrPermission() {
        int roleId = 1;
        int permissionId = 100;

        when(roleRepository.findByRoleId(roleId)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            roleService.addRolePermission(roleId, permissionId);
        });

        assertEquals("Invalid role and permission!", exception.getMessage());
    }

    @Test
    void testDeleteRolePermission() {
        int roleId = 1;
        int permissionId = 100;

        roleService.deleteRolePermission(roleId, permissionId);

        verify(rolePermissionRepository, times(1)).deleteByRoleIdAndPermissionId(roleId, permissionId);
    }
}