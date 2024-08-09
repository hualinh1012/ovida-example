package org.ovida.example.repository;

import org.ovida.example.domain.PermissionEntity;
import org.ovida.example.domain.RolePermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermissionRepository extends JpaRepository<PermissionEntity, Integer>
{
    PermissionEntity findByPermissionId(int permissionId);

}
