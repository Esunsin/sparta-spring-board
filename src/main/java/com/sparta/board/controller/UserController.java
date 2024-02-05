package com.sparta.board.controller;

import com.sparta.board.dto.user.RequestUserDto;
import com.sparta.board.dto.user.ResponseUserDto;
import com.sparta.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseUserDto join(@RequestBody RequestUserDto userDto) {
        return userService.join(userDto);
    }

    @GetMapping
    public List<ResponseUserDto> showAll(){
        return userService.findAll();
    }

}
