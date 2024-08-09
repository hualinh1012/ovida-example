package org.ovida.example.service.impl;

import jakarta.transaction.Transactional;
import org.ovida.example.domain.RoleEntity;
import org.ovida.example.domain.UserEntity;
import org.ovida.example.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService
{

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
    {
        UserEntity user = userRepository.findUserByUserName(username);
        if (user == null)
        {
            return null;
        }
        List<String> roles = user.getRoles().stream().map(RoleEntity::getRoleName).toList();

        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getUserName())
            .password(user.getPassword())
            .roles(roles.toArray(new String[0]))
            .build();
    }

}
