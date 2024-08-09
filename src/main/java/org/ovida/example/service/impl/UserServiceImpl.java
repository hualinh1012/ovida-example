package org.ovida.example.service.impl;

import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import org.ovida.example.adapter.UserAdapter;
import org.ovida.example.domain.UserEntity;
import org.ovida.example.dto.UserDto;
import org.ovida.example.repository.IUserRepository;
import org.ovida.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.ovida.example.constant.RoleConstant.ADMIN;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Override
    public UserDto getCurrentUser() {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = repository.findUserByUserName(currentUser);
        return UserAdapter.toDto(user);
    }

    @Override
    @RolesAllowed(ADMIN)
    public List<UserDto> getAllUser() {
        return UserAdapter.toDTOs(repository.findAll());
    }
}
