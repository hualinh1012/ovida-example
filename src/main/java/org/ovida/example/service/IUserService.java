package org.ovida.example.service;

import org.ovida.example.dto.UserDto;

import java.util.List;

public interface IUserService {
    UserDto getCurrentUser();

    List<UserDto> getAllUser();
}
