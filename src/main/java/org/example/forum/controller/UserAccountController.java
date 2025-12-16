package org.example.forum.controller;

import lombok.RequiredArgsConstructor;
import org.example.forum.dto.UserCreateDto;
import org.example.forum.dto.UserResponseDto;
import org.example.forum.service.UserAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto register(@RequestBody UserCreateDto dto) {
        return service.register(dto);
    }

    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
