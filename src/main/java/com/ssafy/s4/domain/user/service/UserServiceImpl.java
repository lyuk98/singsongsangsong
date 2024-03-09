package com.ssafy.s4.domain.user.service;

import com.ssafy.s4.domain.user.dto.UserDto;
import com.ssafy.s4.domain.user.entity.User;
import com.ssafy.s4.domain.user.repository.UserRepository;
import com.ssafy.s4.global.exception.BaseException;
import com.ssafy.s4.global.common.StatusCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            // throw new BaseException(StatusCode.FAIL);
        }

        return users.stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .createdDate(user.getCreatedDate())
                        .build())
                .collect(Collectors.toList());
    }
}
