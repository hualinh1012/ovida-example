package org.ovida.example.repository;

import org.ovida.example.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, String>
{
    UserEntity findUserByUserName(String userName);
}
