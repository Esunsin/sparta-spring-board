package com.sparta.board.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class RequestUserDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
