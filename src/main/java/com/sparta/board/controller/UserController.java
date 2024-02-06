package com.sparta.board.controller;

import com.sparta.board.dto.user.RequestUserDto;
import com.sparta.board.dto.user.ResponseUserDto;
import com.sparta.board.jwt.JwtAuthenticationFilter;
import com.sparta.board.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @PostMapping("/join")
    public ResponseUserDto join(@Valid RequestUserDto userDto, BindingResult bindingResult)
    {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size()>0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
                throw new IllegalArgumentException("----");
            }
        }

        return userService.join(userDto);
    }

    @GetMapping
    public List<ResponseUserDto> showAll(){
        return userService.findAll();
    }

}
