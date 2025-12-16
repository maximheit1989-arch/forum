package org.example.forum.service;

import org.example.forum.dto.UserCreateDto;
import org.example.forum.dto.UserResponseDto;

public interface UserAccountService {
    UserResponseDto register(UserCreateDto dto);
    UserResponseDto findById(Long id);
}
