package com.sparta.board;

import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserRequestDto {
    @Pattern(regexp = "^[a-z0-9]{4,10}$") //정규식 [들어갈수있는 문자]{최소길이,최대길이}$
    private String username;

    private String password;
}
