package org.ovida.example.repository;

import org.ovida.example.domain.RolePermissionEntity;
import org.ovida.example.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolePermissionRepository extends JpaRepository<RolePermissionEntity, Integer>
{
    void deleteByRoleIdAndPermissionId(int roleId, int permissionId);
}
