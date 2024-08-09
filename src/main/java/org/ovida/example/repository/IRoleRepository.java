package org.ovida.example.repository;

import org.ovida.example.domain.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Integer>
{
    RoleEntity findByRoleId(int roleId);
}
