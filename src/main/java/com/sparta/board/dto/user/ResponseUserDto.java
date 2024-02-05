package com.sparta.board.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseUserDto {
    private String username;

    public ResponseUserDto(String username) {
        this.username = username;
    }
}
