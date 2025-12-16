package org.example.forum.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.forum.dao.UserAccountRepository;
import org.example.forum.dto.UserCreateDto;
import org.example.forum.dto.UserResponseDto;
import org.example.forum.model.UserAccount;
import org.example.forum.service.UserAccountService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository repository;

    @Override
    public UserResponseDto register(UserCreateDto dto) {
        UserAccount user = new UserAccount();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        UserAccount saved = repository.save(user);

        UserResponseDto response = new UserResponseDto();
        response.setId(saved.getId());
        response.setUsername(saved.getUsername());
        response.setEmail(saved.getEmail());

        return response;
    }

    @Override
    public UserResponseDto findById(Long id) {
        UserAccount user = repository.findById(id)
                .orElseThrow(RuntimeException::new);

        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());

        return dto;
    }
}
