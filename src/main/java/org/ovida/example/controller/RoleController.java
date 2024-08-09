package org.ovida.example.controller;

import org.ovida.example.dto.RoleDto;
import org.ovida.example.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }


    @PutMapping("/{roleId}/permissions/{permissionId}")
    public ResponseEntity<?> addRolePermission(@PathVariable int roleId, @PathVariable int permissionId) {
        roleService.addRolePermission(roleId, permissionId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    public ResponseEntity<?> deleteRole(@PathVariable int roleId, @PathVariable int permissionId) {
        roleService.deleteRolePermission(roleId, permissionId);
        return ResponseEntity.noContent().build();
    }

}
