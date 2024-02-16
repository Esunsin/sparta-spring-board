package com.sparta.board;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponseDto {
    private String msg;
    private Integer status;

    public CommonResponseDto(String msg, Integer status) {
        this.msg = msg;
        this.status = status;
    }
}
