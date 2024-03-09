package com.ssafy.s4.domain.user.controller;

import com.ssafy.s4.domain.user.dto.UserDto;
import com.ssafy.s4.domain.user.service.UserService;
import com.ssafy.s4.global.common.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Object>  getAllUsers() {
        List<UserDto> userList = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(new BaseResponse<>(userList));
    }
}