package com.ssafy.s4.domain.user.service;

import com.ssafy.s4.domain.user.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
}
